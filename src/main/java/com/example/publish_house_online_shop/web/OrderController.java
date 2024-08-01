package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private final UserService userService;

    public OrderController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/orders")
    public String viewOrders(){
        if(this.userService.getCurrentUser().get().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        return "orders";
    }
    @GetMapping("/order")
    public String viewOrder(){
        if(this.userService.getCurrentUser().get().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        return "order";
    }
}
