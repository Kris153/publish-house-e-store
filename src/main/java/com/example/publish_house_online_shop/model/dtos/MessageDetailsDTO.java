package com.example.publish_house_online_shop.model.dtos;

public class MessageDetailsDTO {
    private Integer id;

    private String messageTitle;

    private String text;

    private String status;

    private String username;

    private String email;

    public MessageDetailsDTO() {
    }

    public MessageDetailsDTO(Integer id, String messageTitle, String text, String status, String username, String email) {
        this.id = id;
        this.messageTitle = messageTitle;
        this.text = text;
        this.status = status;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
