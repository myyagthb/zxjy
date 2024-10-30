package edu.hut.aiassistant;

import edu.hut.aiassistant.generator.domain.User;
import edu.hut.aiassistant.generator.service.UserService;
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

}
