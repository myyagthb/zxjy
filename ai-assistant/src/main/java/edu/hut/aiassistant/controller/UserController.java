package edu.hut.aiassistant.controller;

import edu.hut.aiassistant.custom.service.UserServiceCustom;
import edu.hut.aiassistant.req.UserReq;
import edu.hut.aiassistant.resp.R;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceCustom userServiceCustom;

    @PostMapping("register")
    public R register(@Valid @RequestBody UserReq userReq){
        return userServiceCustom.register(userReq);
    }

    @PostMapping("login")
    public R login(@Valid @RequestBody UserReq userReq){
        return userServiceCustom.login(userReq);
    }

}
