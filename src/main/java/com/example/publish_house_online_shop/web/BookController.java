package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddBookDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.AuthorService;
import com.example.publish_house_online_shop.service.BookService;
import com.example.publish_house_online_shop.service.CategoryService;
import com.example.publish_house_online_shop.service.UserService;
import com.example.publish_house_online_shop.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
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
    @ModelAttribute("successfulAddToCart")
    public boolean addIsAddedToCartValueToModel(){
        return false;
    }
    @ModelAttribute("titleOfAddedToCartBook")
    public String addTitleOfAddedToCartBookValueToModel(){
        return "";
    }
    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") Integer bookId, Model model){
        model.addAttribute("bookDetails", this.bookService.getBookDetailsDTOById(bookId));
        return "book";
    }
    @GetMapping("")
    public String viewAllBooks(Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("books", this.bookService.getAllBooks());
        return "books";
    }
    @GetMapping("/add-book")
    public String viewAddBook(Model model){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
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
            return "redirect:/books/add-book";
        }
        this.bookService.addBook(bookData);
        redirectAttributes.addFlashAttribute("successfulAddBook", true);
        return "redirect:/";
    }
    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttributes){
        this.bookService.deleteById(bookId);
        redirectAttributes.addFlashAttribute("successfulDeleteBook", true);
        return "redirect:/books";
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView handleObjectNotFound() {
        ModelAndView modelAndView = new ModelAndView("book-not-found");
        return modelAndView;
    }
}
