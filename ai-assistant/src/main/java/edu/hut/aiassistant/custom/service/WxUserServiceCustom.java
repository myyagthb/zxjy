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
