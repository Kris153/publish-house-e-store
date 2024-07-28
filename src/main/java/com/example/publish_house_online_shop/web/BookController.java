package com.example.publish_house_online_shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/book")
    public String viewBook(){
        return "book";
    }
    @GetMapping("/books")
    public String viewAllBooks(){
        return "books";
    }
    @GetMapping("/add-book")
    public String viewAddBook(){
        return "add-book";
    }
}
