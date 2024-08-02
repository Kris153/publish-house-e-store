package com.example.publish_house_online_shop.model.dtos;

import java.util.Objects;

public class BookDetailsForCartDTO {
    private Integer id;
    private String title;

    private Double price;

    public BookDetailsForCartDTO() {
    }

    public BookDetailsForCartDTO(Integer id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDetailsForCartDTO that = (BookDetailsForCartDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
