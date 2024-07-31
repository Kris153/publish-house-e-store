package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.UserRegisterDTO;
import com.example.publish_house_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("registerData")
    public UserRegisterDTO addRegisterDTOToModel(){
        return new UserRegisterDTO();
    }
    @ModelAttribute("hasPasswordErrors")
    private boolean addHasPasswordErrorsValueToModel(){return false;}
    @ModelAttribute("doUsernameExists")
    private boolean addDoUsernameExistsValueToModel(){return false;}
    @ModelAttribute("doEmailExists")
    private boolean addDoEmailExistsValueToModel(){return false;}
    @GetMapping("/register")
    public String viewRegister(){
        return "register";
    }
    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO registerData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() ||
                !this.userService.confirmPassword(registerData) ||
                this.userService.doUsernameExists(registerData) ||
                this.userService.doEmailExists(registerData)
        ){
            redirectAttributes.addFlashAttribute("registerData", registerData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            if(!this.userService.confirmPassword(registerData)){
                redirectAttributes.addFlashAttribute("hasPasswordErrors", true);
            }
            if(this.userService.doUsernameExists(registerData)){
                redirectAttributes.addFlashAttribute("doUsernameExists", true);
            }
            if(this.userService.doEmailExists(registerData)){
                redirectAttributes.addFlashAttribute("doEmailExists", true);
            }
            return "redirect:/register";
        }
        this.userService.register(registerData);
        return "redirect:/login";
    }
}
