package com.example.publish_house_online_shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/profiles")
    public String viewProfiles(){
        return "profiles";
    }
    @GetMapping("/profile")
    public String viewProfile(){
        return "profile";
    }
}
