package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
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
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("categoryData")
    private AddCategoryDTO addAddCategoryDTOToModel(){
        return new AddCategoryDTO();
    }
    @GetMapping("/add-category")
    public String viewAddCategory(){
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@Valid AddCategoryDTO categoryData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("categoryData", categoryData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryData", bindingResult);
            return "redirect:/add-category";
        }
        redirectAttributes.addFlashAttribute("successfulAddCategory", true);
        this.categoryService.addCategory(categoryData);
        return "redirect:/";
    }
}
