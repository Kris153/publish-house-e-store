package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
import com.example.publish_house_online_shop.model.dtos.CategoryDetailsDTO;
import com.example.publish_house_online_shop.service.CategoryService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final RestClient bookRestClient;

    public CategoryServiceImpl(RestClient bookRestClient) {
        this.bookRestClient = bookRestClient;
    }

    @Override
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        this.bookRestClient.post().uri("/add-category").body(addCategoryDTO).retrieve();
    }

    @Override
    public List<CategoryDetailsDTO> getAllCategories() {
        return this.bookRestClient.get().uri("/categories").accept(MediaType.APPLICATION_JSON).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }
}
