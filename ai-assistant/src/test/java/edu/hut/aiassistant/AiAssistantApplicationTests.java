package edu.hut.aiassistant;

import edu.hut.aiassistant.custom.service.UserServiceCustom;
import edu.hut.aiassistant.generator.domain.User;
import edu.hut.aiassistant.generator.service.UserService;
import edu.hut.aiassistant.req.UserInfoReq;
import edu.hut.aiassistant.req.UserReq;
import edu.hut.aiassistant.resp.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class AiAssistantApplicationTests {

    @Test
    void contextLoads() {

    }


    @Autowired
    private UserService userService;

//    @Test
//    void addUser() {
//
//        User user = new User();
//        user.setUserId(10001L);
//        user.setPhone("123");
//        user.setPassword("123");
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
//        userService.save(user);
//    }

    @Autowired
    private UserServiceCustom userServiceCustom;


    @Test
    void testRegister(){
        UserReq userReq = new UserReq();
        userReq.setMobile("14727619000");
        userReq.setPassword("123456");

        R register = userServiceCustom.register(userReq);
        System.out.println(register);
    }


    @Test
    void testLogin(){
        UserReq userReq = new UserReq();
        userReq.setMobile("14727619000");
        userReq.setPassword("123456");

        R register = userServiceCustom.login(userReq);
        System.out.println(register);
    }

    @Test
    void testUpdateUserInfo(){
        UserInfoReq newUser = new UserInfoReq();
        newUser.setUserId(1854788583587618818L);
        newUser.setEducateStage("");
        newUser.setNickname("");
        R r = userServiceCustom.saveOrUpdateUserInfo(newUser);
        System.out.println(r);
    }

}
