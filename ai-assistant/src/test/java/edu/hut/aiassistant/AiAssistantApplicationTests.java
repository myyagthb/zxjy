package edu.hut.aiassistant;

import edu.hut.aiassistant.custom.service.WxUserServiceCustom;
import edu.hut.aiassistant.generator.domain.User;
import edu.hut.aiassistant.generator.service.UserService;
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

    @Autowired
    private WxUserServiceCustom wxUserServiceCustom;

    @Test
    void addUser() {
        User user = new User();
        user.setUserId(10001L);
        user.setPhone("123");
        user.setPassword("123");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.save(user);
    }

    @Test
    void testSendSms(){
        String mobile = "14727619798";
        R resp = wxUserServiceCustom.sendSmsCodeToMobile(mobile);
        System.out.println(resp);



    }
    @Test
    void testLogin(){
        String mobile = "14727619798";
        Integer code = 344768;
        Integer userId = 12334;
        R login = wxUserServiceCustom.login(mobile, code, userId);
        System.out.println(login);
    }

}
