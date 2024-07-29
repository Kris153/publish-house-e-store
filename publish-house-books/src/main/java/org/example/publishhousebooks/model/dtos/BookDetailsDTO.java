package org.example.publishhousebooks.model.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import org.example.publishhousebooks.model.entities.AuthorEntity;
import org.example.publishhousebooks.model.entities.CategoryEntity;

public class BookDetailsDTO {
    private Integer id;

    private String title;

    private String imageUrl;

    private String shortDescription;

    private String longDescription;

    private Double price;

    private String authorName;

    private String categoryName;

    public BookDetailsDTO() {
    }

    public BookDetailsDTO(Integer id, String title, String imageUrl, String shortDescription, String longDescription, Double price, String authorName, String categoryName) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.authorName = authorName;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
