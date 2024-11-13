package edu.hut.aiassistant.custom.service;

import edu.hut.aiassistant.req.UserInfoReq;
import edu.hut.aiassistant.req.UserReq;
import edu.hut.aiassistant.resp.R;

public interface UserServiceCustom {

    R register(UserReq req);

    R login(UserReq req);

    R saveOrUpdateUserInfo(UserInfoReq userInfoReq);


    R getUserInfoById(Long userId);

}
