package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddAuthorDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.AuthorService;
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
public class AuthorController {
    private final AuthorService authorService;
    private final UserService userService;

    public AuthorController(AuthorService authorService, UserService userService) {
        this.authorService = authorService;
        this.userService = userService;
    }
    @ModelAttribute("authorData")
    private AddAuthorDTO addAddAuthorDTOToModel(){
        return new AddAuthorDTO();
    }
    @ModelAttribute("doesAuthorExists")
    private boolean addDoesAuthorExistsValueToModel(){return false;}

    @GetMapping("/authors/{name}")
    public String viewAuthor(@PathVariable("name") String authorName, Model model){
        model.addAttribute("authorDetails", this.authorService.getAuthorDetailsDTOByName(authorName));
        return "author";
    }

    @GetMapping("/add-author")
    public String viewAddAuthor(){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        return "add-author";
    }
    @PostMapping("/add-author")
    public String addAuthor(@Valid AddAuthorDTO authorData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || this.authorService.doesAuthorExists(authorData.getName())){
            redirectAttributes.addFlashAttribute("authorData", authorData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorData", bindingResult);
            if(this.authorService.doesAuthorExists(authorData.getName())){
                redirectAttributes.addFlashAttribute("doesAuthorExists", true);
            }
            return "redirect:/add-author";
        }
        this.authorService.addAuthor(authorData);
        redirectAttributes.addFlashAttribute("successfulAddAuthor", true);
        return "redirect:/";
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView handleObjectNotFound() {
        ModelAndView modelAndView = new ModelAndView("author-not-found");
        return modelAndView;
    }
}
