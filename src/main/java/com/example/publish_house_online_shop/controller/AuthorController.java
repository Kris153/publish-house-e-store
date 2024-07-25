package com.example.publish_house_online_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {
    @GetMapping("/author")
    public String viewAuthor(){

        return "author";
    }

    @GetMapping("/add-author")
    public String viewAddAuthor(){
        return "add-author";
    }
}
