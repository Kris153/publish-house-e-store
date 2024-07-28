package org.example.publishhousebooks.model.dtos;

public class AddCategoryDTO {
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
