package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.UserDetailsDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("cantChangeRole")
    private boolean addCantChangeValueToModel(){return false;}

    @GetMapping("/profiles")
    public String viewProfiles(Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("users", this.userService.getAllUsers());
        return "profiles";
    }
    @GetMapping("/profiles/{id}")
    public String viewProfile(@PathVariable("id") Integer userId, Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("user", this.userService.getUserById(userId));
        return "profile";
    }
    @PatchMapping("/profiles/change-role/{id}")
    public String changeRole(@PathVariable("id") Integer userId, RedirectAttributes redirectAttributes){
        if(!this.userService.changeUserRoleById(userId)){
            redirectAttributes.addFlashAttribute("cantChangeRole", true);
        }
        return "redirect:/profiles/{id}";
    }
}
