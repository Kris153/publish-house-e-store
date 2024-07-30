package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.service.BookService;
import com.example.publish_house_online_shop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {
    private final BookService bookService;
    private final CategoryService categoryService;

    public ShopController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/shop")
    public String viewShop(Model model){
        model.addAttribute("books", this.bookService.getAllBooks());
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "shop";
    }
    @GetMapping("shop/categories/{name}")
    public String getAllBooksByCategory(@PathVariable("name") String categoryName, Model model){
        model.addAttribute("books", this.bookService.getAllBooksByCategory(categoryName));
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "shop";
    }
}
