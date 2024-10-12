package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.custom.entity.WeiXinAccountConfig;
import edu.hut.aiassistant.custom.entity.WeiXinToken;
import edu.hut.aiassistant.custom.entity.WeiXinUserInfo;
import edu.hut.aiassistant.custom.service.WxUserServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.WxUser;
import edu.hut.aiassistant.generator.service.WxUserService;
import edu.hut.aiassistant.req.WxUserReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.utils.HttpRequestUtil;
import edu.hut.aiassistant.utils.ParseXmlForWx;
import edu.hut.aiassistant.utils.WeiXinTemplateUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
public class WxUserServiceCustomImpl implements WxUserServiceCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUserServiceCustomImpl.class);

    @Autowired
    private WeiXinAccountConfig accountConfig;

    // 请求得到的access_token
    JSONObject access_token_obj=null;

    // 记录所有登录人的状态
    //保存用户在线状态，用来平替loginObj变量
    @Resource
    RedisTemplate<String,Boolean> redisTemplate;

    @Autowired
    private WxUserService wxUserService;

    //保存随机数和微信号的关系
    //Map<随机数，用户微信号>
    private static final Map<String,String> connectionMap = new HashMap<>();


    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));


    @Autowired
    private WeiXinTemplateUtils weiXinTemplateUtils;


    /**
     * 获取用户信息二维码
     * @return
     */
    @Override
    public String getUserInfoQrCode() {
        String qrCode = "";
        String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";


        content = content.replace("APPID",accountConfig.getAppId())
                .replace("REDIRECT_URI",accountConfig.getDomain()+accountConfig.getRedirectUri());
        int width = 200;
        int height = 200;
        QrConfig config = new QrConfig(width,height);
        config.setImg(FileUtil.file("haixun.jpeg"));
        qrCode = QrCodeUtil.generateAsBase64(content, config, "png");
        return qrCode;
    }


    /**
     * 用户扫描绑定信息的二维码之后，来到这里获取用户信息，更新用户信息
     * @param code
     * @param state
     * @return
     */

    @Override
    public String updateUserInfo(String code, String state) {
        String userId = null;
        //code-临时授权码
        //获取访问token的地址
        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        tokenUrl = tokenUrl.replace("APPID",accountConfig.getAppId())
                .replace("SECRET",accountConfig.getAppSecret())
                .replace("CODE",code);

        //发送http请求
        //获取带有token和扫码用户的openid参数的信息体
        HttpResponse httpResponse = HttpRequestUtil.doGet(tokenUrl);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200){
            org.apache.http.HttpEntity entity = httpResponse.getEntity();
            try {
                String entiry_string = EntityUtils.toString(entity);

                WeiXinToken token = JSON.parseObject(entiry_string, WeiXinToken.class);
                String access_token = token.getAccess_token();
                String openid = token.getOpenid();

                //给全局access_token_obj赋值
                if (openid != null){
                    userId = openid;
                }

                //获取用户信息的地址
                String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
                userInfoUrl = userInfoUrl.replace("ACCESS_TOKEN",access_token)
                        .replace("OPENID",openid);

                httpResponse = HttpRequestUtil.doGet(userInfoUrl);

                entity = httpResponse.getEntity();
                entiry_string = EntityUtils.toString(entity,"UTF-8");

                //获取到微信服务器提供的用户信息用户信息
                WeiXinUserInfo weiXinUserInfo = JSON.parseObject(entiry_string, WeiXinUserInfo.class);

                LOGGER.info("weiXinUserInfo:{}",weiXinUserInfo);
                //todo 更新数据库
                DateTime now = DateTime.now();
                WxUser wxUser = new WxUser();
                //根据用户微信号查询到用户信息
                LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(WxUser::getOpenid,openid);
                List<WxUser> wxUserList = wxUserService.list(queryWrapper);
                if (wxUserList.size()>0){
                    wxUser = wxUserList.get(0);
                    wxUser.setOpenid(weiXinUserInfo.getOpenid());
                    wxUser.setNickname(weiXinUserInfo.getNickname());
                    wxUser.setGender(weiXinUserInfo.getSex());
                    wxUser.setAvatarUrl(weiXinUserInfo.getHeadimgurl());
                    wxUser.setUpdateTime(now);
                    wxUserService.updateById(wxUser);
                    LOGGER.info("{}信息绑定成功",wxUser);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return userId;
    }


    @Override
    public WxUser getUserInfoById(String userId) {
        LOGGER.info("查询userId为{}用户信息",userId);
        if(userId == null){
            LOGGER.error("userId is null");
            return null;
        }
        //根据用户临时码获取用户的微信号
        String openid = connectionMap.get(userId);

        LOGGER.info("查询openid为{}用户信息",openid);

        //根据openid去查询数据库
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxUser::getOpenid,openid);

        List<WxUser> wxUserList = wxUserService.list(queryWrapper);
        System.out.println(wxUserList);
        for (WxUser wxUser : wxUserList) {
            LOGGER.info("wxUser:{}",wxUser);
        }
        if (!wxUserList.isEmpty()){
            //返回查询结果
            return wxUserList.get(0);
        }
        return null;
    }

    @Override
    public R updateWxUserInfo(WxUserReq wxUserReq) {
        //拷贝对象的值
        WxUser wxUser = BeanUtil.copyProperties(wxUserReq, WxUser.class);
        DateTime now = DateTime.now();
        wxUser.setUpdateTime(now);
        boolean updated = wxUserService.updateById(wxUser);
        if (updated){
            return new R(RespEnum.SUCCESS.getCode(),"更新成功" ,null);
        }
        return new R(RespEnum.SUCCESS.getCode(),"更新失败" ,null);
    }

    @Override
    public String checkRequest(HttpServletRequest req) {

        String signature = req.getParameter("signature");
        String echostr = req.getParameter("echostr");
        System.out.println("请求进来了----: "+signature);

        //校验请求
        String str = this.getValidateStr(req);
        if (str.equals(signature)) {
            System.out.println("请求验证通过-2--");
            // res.send(true)
            return echostr;
        } else {
            System.out.println("请求验证不通过-1--");
            return "fail";
        }
    }



    // 验证信息是否来自微信服务器
    private String getValidateStr(HttpServletRequest req) {
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String[] arr = { accountConfig.getToken(), timestamp, nonce };
        // let arr = [token, timestamp, nonce];
        Arrays.sort(arr);

        String arrStr = String.join("", arr);
        System.out.println("排序后: "+arrStr);

        // 然后通过sha1加密
        // const relStr = sha1(arrStr);
        String sign =sha1_encode(arrStr);
        System.out.println("sha1加密后sign: "+sign);
        return sign;
    }

    // sha1加密方法
    public static String sha1_encode(String str) {
        char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            int len = digest.length;
            for (int j = 0; j < len; j++) {
                sb.append(CHARS[(digest[j] >> 4) & 15]);
                sb.append(CHARS[digest[j] & 15]);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public JSONObject getQrCode(JSONObject jsonObject) {
        /**
         *  获取带参数的二维码的过程包括3步：
         *      微信公众号开发文档：https://developers.weixin.qq.com/doc/offiaccount/Account_Management/Generating_a_Parametric_QR_Code.html
         *      1.获取access_token：文档https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
         *      2.获取ticket
         *      3.用ticket 到指定 URL 换取二维码（这一步由前端完成）
         */

        // 获取access_token
        // 严格说要等待这个请求完毕再继续往下走
        this.getNewToken();
        // 2.获取ticket
        JSONObject json = this.getTicket(jsonObject);
        // 将ticket返回给前端，加载二维码
        return json;
    }


    // 获取新的token:因为token每隔7200秒会过期一次
    public void getNewToken(){
        // 严格来说 access_token的过期时间是 7200秒，在每次请求前判断一下
        if(this.access_token_obj != null){
            System.out.println("已存在token"+this.access_token_obj);
            Long now = new Date().getTime();
            Long lastTime = Long.valueOf(this.access_token_obj.getString("lastTime"));
            Long expires_in = Long.valueOf(this.access_token_obj.getString("expires_in"));
            // 提早30000毫秒请求一次，避免微信服务器阻塞 expires_in是秒的单位，故要乘1000
            if((now - 30000) < (lastTime + expires_in * 1000)){
                System.out.println("未过期，可以用旧的---");
                return;
            }
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // 1.根据前端，或者微信服务器返回来的code，去请求access_token
        String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
                +"appid="+accountConfig.getAppId()
                +"&secret="+accountConfig.getAppSecret();

        System.out.println("uri:"+uri);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(response);
        System.out.println(response.getBody());
        JSONObject obj = JSON.parseObject(response.getBody());

        // 将access_token存储在内存里，备用
        // {"access_token":"63L81ZsSElgw","expires_in":7200}
        // 严格来说 access_token的过期时间是 7200秒，在每次请求前判断一下
        Long now = new Date().getTime();
        obj.put("lastTime", now);
        this.access_token_obj = obj;
        System.out.println("obj"+obj);
    }

    // 获取ticket
    public JSONObject getTicket(JSONObject json){
        System.out.println("前端传来的json："+json);
        // 1.根据access_token请求ticket
        String access_token = this.access_token_obj.getString("access_token");
        String uri = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
                +access_token;
        System.out.println("ticket-uri:"+uri);

        //请求头-法2
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //请求体
        // JSONObject json = new JSONObject();
        // json.put("order_by", "createTime" );
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

        //请求参数
        // 设置全局的默认请求参数.使用场景:比如访问某一个系统,所有的url都需要携带token请求参数,那么就可以使用此设置
        // Map<String,String> map= new HashMap();
        // map.put("apikey","e10adc3949ba59abbe56e057f2gg88dd");
        // restTemplate.setDefaultUriVariables(map);

        //发送请求
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        // 原文链接：https://blog.csdn.net/qq_39466683/article/details/107057891
        System.out.println(response);
        System.out.println(response.getBody());
        JSONObject obj = JSON.parseObject(response.getBody());
        return obj;
    }


    @Override
    public String awaitData(ParseXmlForWx px) {
        System.out.println("接收到的XML为："+px);
        String FromUserName = px.getFromUserName();
        String ToUserName = px.getToUserName();
        String MsgType = px.getMsgType();
        String Event = px.getEvent();
        String fromContent = px.getContent();

        // 这个作为区分哪个用户扫码的
        String EventKey = px.getEventKey();


        //判断该用户是否在短时间内重复登录
        for (Map.Entry<String, String> entry : connectionMap.entrySet()) {
            if (entry.getValue().equals(FromUserName)) {
                //删除现在临时码和微信号的关系
                connectionMap.remove(entry.getKey());

                //删除原来的键值对
                redisTemplate.delete(entry.getKey());
            }
        }


        LOGGER.info("用户的临时key值：{}",EventKey);

        //让它在线60 * 5秒
        redisTemplate.opsForValue().set(EventKey,true, 60 * 5,TimeUnit.SECONDS);
        //this.loginObj.put(EventKey, true);

        //存入随机数和当前用户微信号关系
        connectionMap.put(EventKey, FromUserName);


        System.out.println("展示所有在线用户:");
        // 获取所有的键
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            // 获取每个键对应的值
            Boolean value = redisTemplate.opsForValue().get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }

        Long now = new Date().getTime();

        // 回复信息给 微信服务器
        String content = "";
        if(MsgType.equals("text") ){
            if(fromContent.equals("1") ){
                content = "努力吧！";
            } else if(fromContent.equals("2")){
                content = "再坚持一会，就成功了";
            } else if(fromContent.contains("爱")){
                content = "爱你一万年！";
            } else {
                content = "谢谢！";
            }
        }

        else if(MsgType.equals("event")){
            content = "event事件";
            if(Event.equals("SCAN")){
                content = "手机扫码，登录成功！";
            } else if(Event.equals("subscribe")){
                content = "尊敬的用户，您好，欢迎您的关注！";
            }
            if(Event.equals("unsubscribe")){
                content = "伤心，您取消了关注";
            }
        }
        else{
            content = "其他信息来源！";
        }

        // 根据来时的信息格式，重组返回。(注意中间不能有空格)
        String msgStr = "<xml>"
                +"<ToUserName><![CDATA["+FromUserName+"]]></ToUserName>"
                +"<FromUserName><![CDATA["+ToUserName+"]]></FromUserName>"
                +"<CreateTime>"+now+"</CreateTime>"
                +"<MsgType><![CDATA[text]]></MsgType>"
                +"<Content><![CDATA["+content+"]]></Content>"
                +"</xml>";





        //用线程池发送微信推送消息，没成功，暂时注释
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //保存关注的用户信息
                String accessToken = access_token_obj.getString("access_token");
                weiXinTemplateUtils.sendTemplateMessageToWxUser(FromUserName,accessToken);
            }
        });

        //保存用户信息
        saveWxUserInfo(FromUserName);

        return msgStr;
    }



    //保存用户信息到数据库
    private void saveWxUserInfo(String openId){
        LOGGER.info("用户信息保存方法开始。。。");
        //判断该微信号有没有被添加
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxUser::getOpenid, openId);
        List<WxUser> list = wxUserService.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)){//如果查询到当前微信号，则跳过
            LOGGER.info("该微信用户已存在，用户信息保存方法结束。。。");
            return;
        }
        //没有查询到当前微信号，保存记录到数据库
        //保存信息到数据库
        DateTime nowTime = DateTime.now();
        WxUser wxUser = new WxUser();
        wxUser.setOpenid(openId);
        wxUser.setCreateTime(nowTime);
        wxUser.setUpdateTime(nowTime);
        wxUserService.save(wxUser);
        LOGGER.info("用户信息保存方法结束。。。");
    }

    @Override
    public JSONObject Login(String userId) {
        System.out.println("有人来问"+userId);
        if(redisTemplate !=null){
//            Boolean bool = this.loginObj.get(userId);
            Boolean bool = redisTemplate.opsForValue().get(userId);
            System.out.println("是否登录: "+bool);
            String jsonStr = "";
            if(bool != null){
                jsonStr = "{\"login\":true}";
            } else {
                jsonStr = "{\"login\":false}";
            }
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            return jsonObject;
        } else {
            String jsonStr = "{\"login\":\"false\"}";
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            return jsonObject;
        }
    }

    @Override
    public JSONObject Logout(String userId) {
        LOGGER.info("{}用户执行退出登录操作",userId);
        if(redisTemplate !=null){
            //移除对应的值
            redisTemplate.delete(userId);
            //删除现在临时码和微信号的关系
            connectionMap.remove(userId);
            String jsonStr = "{\"login\":\"true\"}";
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            return jsonObject;
        } else {
            String jsonStr = "{\"login\":\"false\"}";
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            return jsonObject;
        }
    }

    @Override
    public Boolean judgeUserIsOnline(String userId) {
        // 获取当前键的剩余过期时间（以秒为单位）
        Long ttl = redisTemplate.getExpire(userId, TimeUnit.SECONDS);
        if (ttl == null || ttl < 0) {
            // 如果键没有设置过期时间或者已经过期
            return false;
        }
        // 计算新的过期时间
        long newTtl = ttl + 30;

        //返回用在线状态
        // 设置新的过期时间
        redisTemplate.expire(userId, newTtl, TimeUnit.SECONDS);

        Boolean userIsOnline = redisTemplate.opsForValue().get(userId);

        String status = Boolean.TRUE.equals(userIsOnline) ? "在线" : "离线";

        LOGGER.info("用户ID为：{}更新在线状态,当前状态为：{}",userId,status);

        //返回用户在线状态
        return userIsOnline;
    }

}
