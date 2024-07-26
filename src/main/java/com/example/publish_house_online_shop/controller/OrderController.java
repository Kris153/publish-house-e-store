package com.example.publish_house_online_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/orders")
    public String viewOrders(){
        return "orders";
    }
    @GetMapping("/order")
    public String viewOrder(){
        return "order";
    }
}
