package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
import com.example.publish_house_online_shop.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final RestClient bookRestClient;

    public CategoryServiceImpl(RestClient bookRestClient) {
        this.bookRestClient = bookRestClient;
    }

    @Override
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        this.bookRestClient.post().uri("http://localhost:8081/add-category").body(addCategoryDTO).retrieve();
    }
}
