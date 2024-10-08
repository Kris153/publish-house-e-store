package com.example.publish_house_online_shop.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private boolean isDeleted;

    public BookEntity() {
    }

    public BookEntity(Integer id, String title, Double price, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isDeleted = isDeleted;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
