package com.example.publish_house_online_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/add-category")
    public String viewAddCategory(){
        return "add-category";
    }
}
