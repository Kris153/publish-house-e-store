package com.example.publish_house_online_shop.model.dtos;

import java.util.HashMap;
import java.util.Map;

public class CartDetailsDTO {
    private Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap;
    private Double totalPrice;
    private String promoCodeName;

    public CartDetailsDTO() {
    }

    public CartDetailsDTO(Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap, Double totalPrice, String promoCodeName) {
        this.booksQuantitiesMap = booksQuantitiesMap;
        this.totalPrice = totalPrice;
        this.promoCodeName = promoCodeName;
    }

    public Map<BookDetailsForCartDTO, Integer> getBooksQuantitiesMap() {
        return booksQuantitiesMap;
    }

    public void setBooksQuantitiesMap(Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap) {
        this.booksQuantitiesMap = booksQuantitiesMap;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPromoCodeName() {
        return promoCodeName;
    }

    public void setPromoCodeName(String promoCodeName) {
        this.promoCodeName = promoCodeName;
    }
}
