package com.example.publish_house_online_shop.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class AddMessageDTO {
    @NotBlank
    private String messageTitle;
    @NotBlank
    private String text;

    public AddMessageDTO() {
    }

    public AddMessageDTO(String messageTitle, String text) {
        this.messageTitle = messageTitle;
        this.text = text;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
