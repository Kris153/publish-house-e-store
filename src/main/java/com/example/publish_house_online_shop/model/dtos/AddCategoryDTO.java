package com.example.publish_house_online_shop.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class AddCategoryDTO {
    @NotBlank
    private String name;

    public AddCategoryDTO() {
    }

    public AddCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
