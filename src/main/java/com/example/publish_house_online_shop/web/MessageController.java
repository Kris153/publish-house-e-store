package com.example.publish_house_online_shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/messages")
    public String viewMessages(){
        return "messages";
    }
    @GetMapping("/message")
    public String viewMessage(){
        return "message";
    }
}
