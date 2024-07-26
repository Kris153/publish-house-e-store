package com.example.publish_house_online_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    @ModelAttribute("isOrdered")
    public boolean addIsOrderedValueToModel(){
        return false;
    }
    @GetMapping("/index")
    public String viewHomePage(Model model){
        return "index";
    }
}
