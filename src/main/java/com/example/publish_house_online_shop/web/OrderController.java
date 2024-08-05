package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.OrderDetailsDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.OrderService;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String viewOrders(Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("orders", this.orderService.getAllOrders());
        return "orders";
    }
    @GetMapping("/orders/{id}")
    public String viewOrder(@PathVariable("id") Integer orderId, Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("order", this.orderService.getOrderById(orderId));
        return "order";
    }
    @PatchMapping("/orders/change-status/{id}")
    public String changeOrderStatusToCompletedById(@PathVariable("id") Integer orderId){
        this.orderService.changeOrderStatusToCompletedById(orderId);
        return "redirect:/orders/{id}";
    }
    @GetMapping("/orders/user/{id}")
    public String viewOrdersByUserId(@PathVariable("id") Integer userId, Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("orders", this.orderService.getAllOrderByUserId(userId));
        return "orders";
    }
}
