package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
    @ModelAttribute("hasErrors")
    private boolean addHasErrorsValueToModel(){return false;}
    @ModelAttribute("loginData")
    private UserLoginDTO addUserLoginDTOToModel(){
        return new UserLoginDTO();
    }
    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }
    @GetMapping("/login-error")
    public String viewLoginError(Model model){
        model.addAttribute("hasErrors", true);
        return "login";
    }
}
