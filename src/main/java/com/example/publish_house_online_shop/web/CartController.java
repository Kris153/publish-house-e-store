package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.BookDetailsForCartDTO;
import com.example.publish_house_online_shop.model.dtos.CartDetailsDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.BookService;
import com.example.publish_house_online_shop.service.CartService;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.FileNameMap;
import java.util.Collection;
import java.util.Map;

@Controller
public class CartController {
    private final UserService userService;
    private final CartService cartService;
    private final BookService bookService;

    public CartController(UserService userService, CartService cartService, BookService bookService) {
        this.userService = userService;
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model){
        CartDetailsDTO currentCart = this.userService.getCurrentCart();
        model.addAttribute("cart", currentCart);
        return "cart";
    }
    @PutMapping("/book/add-to-cart/{id}")
    public String bookAddToCartById(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttributes){
        this.cartService.addToCartById(bookId, this.userService.getCurrentUser().get());
        redirectAttributes.addFlashAttribute("successfulAddToCart", true);
        redirectAttributes.addFlashAttribute("titleOfAddedToCartBook", this.bookService.getBookDetailsDTOById(bookId).getTitle());
        return "redirect:/books/{id}";
    }
    @PutMapping("/shop/add-to-cart/{id}")
    public String shopAddToCartById(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttributes){
        this.cartService.addToCartById(bookId, this.userService.getCurrentUser().get());
        redirectAttributes.addFlashAttribute("successfulAddToCart", true);
        redirectAttributes.addFlashAttribute("titleOfAddedToCartBook", this.bookService.getBookDetailsDTOById(bookId).getTitle());
        return "redirect:/shop";
    }
    @PutMapping("/cart/add-book/{id}")
    public String cartAddToCartById(@PathVariable("id") Integer bookId){
        this.cartService.addToCartById(bookId, this.userService.getCurrentUser().get());
        return "redirect:/cart";
    }
    @PutMapping("/cart/remove-book/{id}")
    public String cartRemoveFromCartById(@PathVariable("id") Integer bookId){
        this.cartService.removeFromCartById(bookId, this.userService.getCurrentUser().get());
        return "redirect:/cart";
    }
}
