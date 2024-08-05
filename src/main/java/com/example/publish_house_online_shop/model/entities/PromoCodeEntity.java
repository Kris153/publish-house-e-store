package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.PromoCodeStatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "promo_codes")
public class PromoCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer discountPercent;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PromoCodeStatusEnum status;

    public PromoCodeEntity() {
    }

    public PromoCodeEntity(Integer id, String name, Integer discountPercent, PromoCodeStatusEnum status) {
        this.id = id;
        this.name = name;
        this.discountPercent = discountPercent;
        this.status = status;
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

    public PromoCodeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PromoCodeStatusEnum status) {
        this.status = status;
    }
}
