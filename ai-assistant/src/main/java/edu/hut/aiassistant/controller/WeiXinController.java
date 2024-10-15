package edu.hut.aiassistant.controller;


import com.alibaba.fastjson.JSONObject;
import edu.hut.aiassistant.custom.dto.WebChatRequestDTO;
import edu.hut.aiassistant.custom.service.WxUserServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.WxUser;
import edu.hut.aiassistant.req.WxUserReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.utils.ParseXml;
import edu.hut.aiassistant.utils.ParseXmlForWx;
import edu.hut.aiassistant.utils.WebChatUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
public class WeiXinController {



    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinController.class);


    @Autowired
    private WxUserServiceCustom wxUserServiceCustom;





    @GetMapping("/")
    public String checkWebChat(WebChatRequestDTO requestDTO) throws NoSuchAlgorithmException {
        LOGGER.info("验证微信服务器发送的请求");
        if (WebChatUtils.checkWebChatToken(requestDTO)){
            LOGGER.info("请求验证通过");
            return requestDTO.getEchostr();
        }
        LOGGER.info("请求验失败");
        return "fail";
    }








    @GetMapping("/qrUserInfoCode")
    public String getUserInfoQrCode(){
        return wxUserServiceCustom.getUserInfoQrCode();
    }


    @GetMapping("/saveUserInfo")
    public String saveUserInfo(String code, String state){
        System.out.println("code = " + code);
        if (code == null){
            return null;
        }
        String userId = wxUserServiceCustom.updateUserInfo(code, state);
        return "信息绑定成功，请关闭网页二维码界面！";
    }



    @GetMapping("/getWxUserInfoById")
    public R getWxUserInfoById(@RequestParam("userId") String userId){
        WxUser wxUser = wxUserServiceCustom.getUserInfoById(userId);
        return new R(RespEnum.SUCCESS.getCode(), "查询成功",wxUser);
    }

    @PostMapping("/updateWxUserInfo")
    public R updateWxUserInfo(@RequestBody WxUserReq wxUserReq){
        return wxUserServiceCustom.updateWxUserInfo(wxUserReq);
    }

















//    @GetMapping("/")
//    public String kk(HttpServletRequest req){
//        return wxUserServiceCustom.checkRequest(req);
//    }

    @PostMapping("/getQrCode")
    public JSONObject getQrCode(@RequestBody JSONObject jsonObject){
        return wxUserServiceCustom.getQrCode(jsonObject);
    }

    // 当用户用手机扫码时，微信服务器会通过post方法给我们传递数据
    @PostMapping("/")
    public String awaitData(@RequestBody ParseXmlForWx px){
        if(px.getEventKey() == null){
            //这里判断一下，解决重复请求这个值为空，导致Redis缓冲访问失败问题
            return null;
        }
        System.out.println("控制层的xml对象为："+px);
        wxUserServiceCustom.awaitData(px);
        //把原来的文字提示改成消息模板提示
        return  null;
    }

    @PostMapping("parseXml")
    public ParseXml parseXml(@RequestBody ParseXml px){
        System.out.println("ParseXml: "+px);
        return px;
    }
    @PostMapping("parseXmlForWx")
    public ParseXmlForWx parseXml(@RequestBody ParseXmlForWx px){
        System.out.println("ParseXmlForWx: "+px);
        return px;
    }

    @GetMapping("Login")
    public JSONObject Login(@RequestParam("userId") String userId){
        return wxUserServiceCustom.Login(userId);
    }


    @GetMapping("Logout")
    public JSONObject Logout(@RequestParam("userId") String userId){
        return wxUserServiceCustom.Logout(userId);
    }


    @GetMapping("/judgeUserIsOnline")
    public R judgeUserIsOnline(@RequestParam("userId") String userId){
        Boolean userIsOnline = wxUserServiceCustom.judgeUserIsOnline(userId);
        return new R(RespEnum.SUCCESS.getCode(),"心跳消息更新成功" ,userIsOnline);
    }




//    // 调用微信授权接口重定向
//    @GetMapping("/redirect")
//    public String toRedirectUrl(HttpServletResponse response){
//        System.out.println("到这里111");
//        return wxUserServiceCustom.toRedirectUrl(response);
//    }
//
//    // 授权接口重定向回调方法
//    @GetMapping("/redirect/info")
//    public R redirectInfo(@RequestParam(value = "code") String code,
//                          @RequestParam(value = "state", required = false) String state,
//                          HttpServletResponse response){
//        System.out.println("到这里222");
//        return wxUserServiceCustom.redirectInfo(code,state,response);
//    }

}
