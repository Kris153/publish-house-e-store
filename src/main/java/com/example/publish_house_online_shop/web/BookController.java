package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddBookDTO;
import com.example.publish_house_online_shop.model.dtos.BookDetailsDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.AuthorService;
import com.example.publish_house_online_shop.service.BookService;
import com.example.publish_house_online_shop.service.CategoryService;
import com.example.publish_house_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final UserService userService;

    public BookController(BookService bookService, CategoryService categoryService, AuthorService authorService, UserService userService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.userService = userService;
    }
    @ModelAttribute("bookData")
    private AddBookDTO addAddBookDTOToModel(){
        return new AddBookDTO();
    }
    @ModelAttribute("successfulDeleteBook")
    public boolean addIsDeletedBookValueToModel(){
        return false;
    }
    @GetMapping("/books/{id}")
    public String viewBook(@PathVariable("id") Integer bookId, Model model){
        model.addAttribute("bookDetails", this.bookService.getBookDetailsDTOById(bookId));
        return "book";
    }
    @GetMapping("/books")
    public String viewAllBooks(Model model){
        if(this.userService.getCurrentUser().get().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("books", this.bookService.getAllBooks());
        return "books";
    }
    @GetMapping("/add-book")
    public String viewAddBook(Model model){
        if(this.userService.getCurrentUser().get().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
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
        return "redirect:/";
    }
    @DeleteMapping("/books/{id}")
    public String deleteBookById(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttributes){
        this.bookService.deleteById(bookId);
        redirectAttributes.addFlashAttribute("successfulDeleteBook", true);
        return "redirect:/books";
    }
}
