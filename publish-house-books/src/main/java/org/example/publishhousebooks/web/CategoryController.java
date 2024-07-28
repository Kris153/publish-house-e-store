package org.example.publishhousebooks.web;

import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.repository.CategoryRepository;
import org.example.publishhousebooks.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add-category")
    public ResponseEntity<AddCategoryDTO> addCategory(@RequestBody AddCategoryDTO addCategoryDTO){
        categoryService.addCategory(addCategoryDTO);
        return ResponseEntity.ok().build();
    }
}
