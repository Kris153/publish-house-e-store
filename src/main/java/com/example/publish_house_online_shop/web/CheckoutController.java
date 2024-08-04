package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddOrderDTO;
import com.example.publish_house_online_shop.model.dtos.CartDetailsDTO;
import com.example.publish_house_online_shop.service.CheckoutService;
import com.example.publish_house_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CheckoutController {
    private final UserService userService;
    private final CheckoutService checkoutService;

    public CheckoutController(UserService userService, CheckoutService checkoutService) {
        this.userService = userService;
        this.checkoutService = checkoutService;
    }
    @ModelAttribute("orderData")
    private AddOrderDTO addAddOrderDTOToModel(){
        return new AddOrderDTO();
    }
    @GetMapping("/checkout")
    public String viewCheckout(Model model){
        CartDetailsDTO currentCart = this.userService.getCurrentCart();
        if(currentCart.getBooksQuantitiesMap().isEmpty()){
            return "redirect:/empty-cart";
        }
        model.addAttribute("cart", currentCart);
        return "checkout";
    }
    @PostMapping("/checkout/finish-order")
    public String finishOrder(@Valid AddOrderDTO orderData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderData", orderData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderData", bindingResult);
            return "redirect:/checkout";
        }
        redirectAttributes.addFlashAttribute("isOrdered", true);
        this.checkoutService.finishOrder(orderData, this.userService.getCurrentUser(), this.userService.getCurrentCart());
        return "redirect:/";
    }
    @PostMapping("/checkout/promo-code")
    public String usePromoCode(){
        return "index";
    }
}
