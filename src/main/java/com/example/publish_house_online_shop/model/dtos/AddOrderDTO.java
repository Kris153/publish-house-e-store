package com.example.publish_house_online_shop.model.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AddOrderDTO {
    @NotBlank
    private String shippingName;
    @NotBlank
    private String shippingEmail;
    @NotBlank
    private String shippingPhoneNumber;
    @NotBlank
    private String shippingAddress;

    public AddOrderDTO() {
    }

    public AddOrderDTO(String shippingName, String shippingEmail, String shippingPhoneNumber, String shippingAddress) {
        this.shippingName = shippingName;
        this.shippingEmail = shippingEmail;
        this.shippingPhoneNumber = shippingPhoneNumber;
        this.shippingAddress = shippingAddress;
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
}
