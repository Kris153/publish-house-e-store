package org.example.publishhousebooks.service.impl;

import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.model.entities.CategoryEntity;
import org.example.publishhousebooks.repository.CategoryRepository;
import org.example.publishhousebooks.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        CategoryEntity categoryToAdd = this.modelMapper.map(addCategoryDTO, CategoryEntity.class);
        this.categoryRepository.saveAndFlush(categoryToAdd);
    }
}
