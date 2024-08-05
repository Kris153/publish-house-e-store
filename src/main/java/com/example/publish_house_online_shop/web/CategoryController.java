package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.CategoryService;
import com.example.publish_house_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @ModelAttribute("categoryData")
    private AddCategoryDTO addAddCategoryDTOToModel(){
        return new AddCategoryDTO();
    }
    @ModelAttribute("doesCategoryExists")
    private boolean addDoesCategoryExistsValueToModel(){return false;}
    @GetMapping("/add-category")
    public String viewAddCategory(){
        if(this.userService.getCurrentUser().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@Valid AddCategoryDTO categoryData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || this.categoryService.doesCategoryExists(categoryData.getName())){
            redirectAttributes.addFlashAttribute("categoryData", categoryData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryData", bindingResult);
            if(this.categoryService.doesCategoryExists(categoryData.getName())){
                redirectAttributes.addFlashAttribute("doesCategoryExists", true);
            }
            return "redirect:/add-category";
        }
        redirectAttributes.addFlashAttribute("successfulAddCategory", true);
        this.categoryService.addCategory(categoryData);
        return "redirect:/";
    }
}
