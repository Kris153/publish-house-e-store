package org.example.publishhousebooks.web;

import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.model.dtos.CategoryDetailsDTO;
import org.example.publishhousebooks.repository.CategoryRepository;
import org.example.publishhousebooks.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDetailsDTO>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
}
