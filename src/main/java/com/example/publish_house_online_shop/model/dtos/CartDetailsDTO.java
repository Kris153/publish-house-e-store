package com.example.publish_house_online_shop.model.dtos;

import java.util.HashMap;
import java.util.Map;

public class CartDetailsDTO {
    private Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap;
    private Double totalPrice;

    public CartDetailsDTO() {
        this.booksQuantitiesMap = new HashMap<>();
    }

    public CartDetailsDTO(Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap, Double totalPrice) {
        this.booksQuantitiesMap = booksQuantitiesMap;
        this.totalPrice = totalPrice;
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
}
