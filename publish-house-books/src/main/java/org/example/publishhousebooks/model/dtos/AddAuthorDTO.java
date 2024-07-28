package org.example.publishhousebooks.model.dtos;

public class AddAuthorDTO {
    private String name;

    private String imageUrl;

    private String description;

    public AddAuthorDTO() {
    }

    public AddAuthorDTO(String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
