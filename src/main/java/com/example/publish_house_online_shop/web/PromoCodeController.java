package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddPromoCodeDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.PromoCodeService;
import com.example.publish_house_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PromoCodeController {
    private final UserService userService;
    private final PromoCodeService promoCodeService;

    public PromoCodeController(UserService userService, PromoCodeService promoCodeService) {
        this.userService = userService;
        this.promoCodeService = promoCodeService;
    }
    @ModelAttribute("promoCodeData")
    private AddPromoCodeDTO addAddPromoCodeDTOToModel(){return new AddPromoCodeDTO();}
    @ModelAttribute("doesPromoCodeExists")
    private boolean addDoesPromoCodeExistsValueToModel(){return false;}

    @GetMapping("/add-promo-code")
    public String viewAddPromoCode(){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        return "add-promo-code";
    }
    @GetMapping("/promo-codes")
    public String viewPromoCodes(Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("promoCodes", this.promoCodeService.getAllPromoCodes());
        return "promo-codes";
    }
    @PostMapping("/add-promo-code")
    public String addPromoCode(@Valid AddPromoCodeDTO promoCodeData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || this.promoCodeService.doesPromoCodeExists(promoCodeData.getName())){
            redirectAttributes.addFlashAttribute("promoCodeData", promoCodeData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.promoCodeData", bindingResult);
            if(this.promoCodeService.doesPromoCodeExists(promoCodeData.getName())){
                redirectAttributes.addFlashAttribute("doesPromoCodeExists", true);
            }
            return "redirect:/add-promo-code";
        }
        this.promoCodeService.addPromoCode(promoCodeData);
        redirectAttributes.addFlashAttribute("successfulAddPromoCode", true);
        return "redirect:/";
    }
    @PatchMapping("/promo-codes/change-status/{id}")
    public String changePromoCodeStatusById(@PathVariable("id") Integer promoCodeId){
        this.promoCodeService.changeStatusById(promoCodeId);
        return "redirect:/promo-codes";
    }
    @DeleteMapping("/promo-codes/{id}")
    public String deletePromoCodeById(@PathVariable("id") Integer promoCodeId){
        this.promoCodeService.deleteById(promoCodeId);
        return "redirect:/promo-codes";
    }
}
