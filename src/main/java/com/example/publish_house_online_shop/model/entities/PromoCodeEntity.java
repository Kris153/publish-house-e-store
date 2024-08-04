package com.example.publish_house_online_shop.model.entities;

import jakarta.persistence.*;

@Table
@Entity
public class PromoCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer discountPercent;

    public PromoCodeEntity() {
    }

    public PromoCodeEntity(Integer id, String name, Integer discountPercent) {
        this.id = id;
        this.name = name;
        this.discountPercent = discountPercent;
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

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }
}
