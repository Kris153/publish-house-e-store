package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CheckoutController {
    private final UserService userService;

    public CheckoutController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkout")
    public String viewCheckout(Model model){

        return "checkout";
    }
    @GetMapping("/checkout/finish-order")
    public String finishOrder(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("isOrdered", true);
        return "redirect:/index";
    }
}
