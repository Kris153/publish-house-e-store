package org.example.publishhousebooks.model.dtos;

public class CategoryDetailsDTO {
    private Integer id;
    private String name;

    public CategoryDetailsDTO() {
    }

    public CategoryDetailsDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
