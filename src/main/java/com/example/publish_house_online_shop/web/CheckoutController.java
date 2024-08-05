package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddOrderDTO;
import com.example.publish_house_online_shop.model.dtos.CartDetailsDTO;
import com.example.publish_house_online_shop.model.dtos.UsePromoCodeDTO;
import com.example.publish_house_online_shop.service.CheckoutService;
import com.example.publish_house_online_shop.service.PromoCodeService;
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
    private final PromoCodeService promoCodeService;

    public CheckoutController(UserService userService, CheckoutService checkoutService, PromoCodeService promoCodeService) {
        this.userService = userService;
        this.checkoutService = checkoutService;
        this.promoCodeService = promoCodeService;
    }
    @ModelAttribute("orderData")
    private AddOrderDTO addAddOrderDTOToModel(){
        return new AddOrderDTO();
    }
    @ModelAttribute("promoCodeData")
    private UsePromoCodeDTO addUsePromoCodeDTOToModel(){return new UsePromoCodeDTO();}
    @ModelAttribute("doesPromoCodeExists")
    private boolean addDoesPromoCodeExistsValueToModel(){return true;}
    @ModelAttribute("isPromoCodeActive")
    private boolean addIsPromoCodeActiveValueToModel(){return true;}
    @ModelAttribute("successfulUsePromoCode")
    private boolean addSuccessfulUsePromoCodeValueToModel(){return false;}
    @ModelAttribute("discountPercent")
    private Integer addDiscountPercentValueToModel(){return 0;}
    @ModelAttribute("promoCodeName")
    private String addPromoCodeNameValueToModel(){return "";}
    @GetMapping("/checkout")
    public String viewCheckout(Model model){
        if(this.userService.isCurrentCartEmpty()){
            return "redirect:/empty-cart";
        }
        model.addAttribute("cart", this.userService.getCurrentCart());
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
    public String usePromoCode(UsePromoCodeDTO promoCodeData, RedirectAttributes redirectAttributes){
        if(!this.promoCodeService.doesPromoCodeExists(promoCodeData)){
            redirectAttributes.addFlashAttribute("doesPromoCodeExists", false);
            return "redirect:/checkout";
        }
        if(!this.promoCodeService.isPromoCodeActive(promoCodeData)){
            redirectAttributes.addFlashAttribute("isPromoCodeActive", false);
            return "redirect:/checkout";
        }
        this.userService.usePromoCode(promoCodeData);
        redirectAttributes.addFlashAttribute("successfulUsePromoCode", true);
        redirectAttributes.addFlashAttribute("discountPercent", this.promoCodeService.getDiscountPercent(promoCodeData));
        redirectAttributes.addFlashAttribute("promoCodeName", this.promoCodeService.getName(promoCodeData));
        return "redirect:/checkout";
    }
}
