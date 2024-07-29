package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
import com.example.publish_house_online_shop.model.dtos.CategoryDetailsDTO;

import java.util.List;

public interface CategoryService {
    void addCategory(AddCategoryDTO addCategoryDTO);
    List<CategoryDetailsDTO> getAllCategories();
}
