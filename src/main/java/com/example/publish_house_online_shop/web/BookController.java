package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddBookDTO;
import com.example.publish_house_online_shop.service.AuthorService;
import com.example.publish_house_online_shop.service.BookService;
import com.example.publish_house_online_shop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public BookController(BookService bookService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }
    @ModelAttribute("bookData")
    private AddBookDTO addAddBookDTOToModel(){
        return new AddBookDTO();
    }
    @GetMapping("/book")
    public String viewBook(){
        return "book";
    }
    @GetMapping("/books")
    public String viewAllBooks(){
        return "books";
    }
    @GetMapping("/add-book")
    public String viewAddBook(Model model){
        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("authors", this.authorService.getAllAuthors());
        return "add-book";
    }
    @PostMapping("/add-book")
    public String addBook(@Valid AddBookDTO bookData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("bookData", bookData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookData", bindingResult);
            return "redirect:/add-book";
        }
        this.bookService.addBook(bookData);
        redirectAttributes.addFlashAttribute("successfulAddBook", true);
        return "redirect:/index";
    }
}
