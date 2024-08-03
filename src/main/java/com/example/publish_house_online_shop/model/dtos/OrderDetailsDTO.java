package com.example.publish_house_online_shop.model.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.Map;

public class OrderDetailsDTO {
    private Integer id;
    private String username;

    private String shippingName;

    private String shippingEmail;

    private String shippingPhoneNumber;

    private String shippingAddress;

    private Instant createdDateTime;

    private Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap;

    private Double totalPrice;

    private String status;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(Integer id, String username, String shippingName, String shippingEmail, String shippingPhoneNumber, String shippingAddress, Instant createdDateTime, Map<BookDetailsForCartDTO, Integer> booksQuantitiesMap, Double totalPrice, String status) {
        this.id = id;
        this.username = username;
        this.shippingName = shippingName;
        this.shippingEmail = shippingEmail;
        this.shippingPhoneNumber = shippingPhoneNumber;
        this.shippingAddress = shippingAddress;
        this.createdDateTime = createdDateTime;
        this.booksQuantitiesMap = booksQuantitiesMap;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingEmail() {
        return shippingEmail;
    }

    public void setShippingEmail(String shippingEmail) {
        this.shippingEmail = shippingEmail;
    }

    public String getShippingPhoneNumber() {
        return shippingPhoneNumber;
    }

    public void setShippingPhoneNumber(String shippingPhoneNumber) {
        this.shippingPhoneNumber = shippingPhoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
