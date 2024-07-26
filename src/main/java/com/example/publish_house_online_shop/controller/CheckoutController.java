package com.example.publish_house_online_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CheckoutController {
    @GetMapping("/checkout")
    public String viewCheckout(){
        return "checkout";
    }
    @GetMapping("/checkout/finish-order")
    public String finishOrder(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("isOrdered", true);
        return "redirect:/index";
    }
}
