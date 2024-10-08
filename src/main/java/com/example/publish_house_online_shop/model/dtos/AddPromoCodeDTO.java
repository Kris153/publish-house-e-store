package com.example.publish_house_online_shop.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddPromoCodeDTO {
    @NotBlank
    private String name;
    @NotNull
    private Integer discountPercent;

    public AddPromoCodeDTO() {
    }

    public AddPromoCodeDTO(String name, Integer discountPercent) {
        this.name = name;
        this.discountPercent = discountPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }
}
