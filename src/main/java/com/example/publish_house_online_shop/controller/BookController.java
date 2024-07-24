package com.example.publish_house_online_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/book")
    public String viewBook(){

        return "book";
    }
}
