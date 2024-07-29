package org.example.publishhousebooks.service;

import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.model.dtos.CategoryDetailsDTO;

import java.util.List;

public interface CategoryService {
    void addCategory(AddCategoryDTO addCategoryDTO);
    List<CategoryDetailsDTO> getAllCategories();
}
