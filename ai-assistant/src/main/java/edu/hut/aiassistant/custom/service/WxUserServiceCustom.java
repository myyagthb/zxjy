package edu.hut.aiassistant.custom.service;

import com.alibaba.fastjson.JSONObject;
import edu.hut.aiassistant.generator.domain.WxUser;
import edu.hut.aiassistant.req.WxUserReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.utils.ParseXmlForWx;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;


public interface WxUserServiceCustom {

    //======================以下是短信验证登录部分==========

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    R sendSmsCodeToMobile(String mobile);


    /**
     * 短信验证登录
     * @param mobile
     * @param code
     * @return
     */
    R login(String mobile,Integer code,Integer userId);




    //======================以下微信扫码登录部分=============

    //获取用户信息二维码
    String getUserInfoQrCode();

    String updateUserInfo(String code,String state);

    /**
     * 根据临时Id查询到用户信息
     * @param userId
     * @return
     */
    WxUser getUserInfoById(String userId);


    R updateWxUserInfo(WxUserReq wxUserReq);













    String checkRequest(HttpServletRequest req);




    JSONObject getQrCode(JSONObject jsonObject);


    String awaitData(ParseXmlForWx px);

    JSONObject Login(String userId);

    JSONObject Logout(String userId);

    Boolean judgeUserIsOnline(String userId);


}
