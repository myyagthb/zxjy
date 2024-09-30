package edu.hut.aiassistant.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/success")
    public String success(){
        return "The server is running normally";
    }
}
