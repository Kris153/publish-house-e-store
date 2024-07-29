package com.example.publish_house_online_shop.model.dtos;

import jakarta.validation.constraints.*;

public class AddBookDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String authorName;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String shortDescription;
    @NotBlank
    private String longDescription;
    @NotNull
    private Double price;

    public AddBookDTO() {
    }

    public AddBookDTO(String title, String imageUrl, String authorName, String categoryName, String shortDescription, String longDescription, Double price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.authorName = authorName;
        this.categoryName = categoryName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
