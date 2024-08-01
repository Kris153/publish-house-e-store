package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    private final UserService userService;

    public CartController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String viewCart(){

        return "cart";
    }
}
