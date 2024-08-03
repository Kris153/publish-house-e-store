package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String viewAdmin(Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("username", this.userService.getCurrentUser().getUsername());
        return "admin";
    }
}
