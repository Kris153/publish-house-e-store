package com.example.publish_house_online_shop.model.dtos;

public class PromoCodeDetailsDTO {
    private Integer id;

    private String name;

    private Integer discountPercent;

    private String status;

    public PromoCodeDetailsDTO() {
    }

    public PromoCodeDetailsDTO(Integer id, String name, Integer discountPercent, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
