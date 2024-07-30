package com.example.publish_house_online_shop.model.dtos;

public class CategoryDetailsDTO {
    private Integer id;

    private String name;
    private Integer numberOfBooks;

    public CategoryDetailsDTO() {
    }

    public CategoryDetailsDTO(Integer id, String name, Integer numberOfBooks) {
        this.id = id;
        this.name = name;
        this.numberOfBooks = numberOfBooks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
