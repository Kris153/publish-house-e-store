package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("isOrdered")
    public boolean addIsOrderedValueToModel(){
        return false;
    }
    @ModelAttribute("successfulAddCategory")
    public boolean addIsAddedCategoryValueToModel(){
        return false;
    }
    @ModelAttribute("successfulAddAuthor")
    public boolean addIsAddedAuthorValueToModel(){
        return false;
    }
    @ModelAttribute("successfulAddBook")
    public boolean addIsAddedBookValueToModel(){
        return false;
    }
    @ModelAttribute("successfulAddMessage")
    private boolean addIsAddedMessageValueToModel(){
        return false;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("books", this.bookService.getAllBooks());
        return "index";
    }

}
