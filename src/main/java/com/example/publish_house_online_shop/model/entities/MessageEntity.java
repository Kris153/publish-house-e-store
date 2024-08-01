package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.MessageStatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String title;
    @Enumerated(value = EnumType.STRING)
    @Column
    private MessageStatusEnum status;
    @ManyToOne
    private UserEntity user;

    public MessageEntity() {
    }

    public MessageEntity(Integer id, String text, String title, MessageStatusEnum status, UserEntity user) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.status = status;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MessageStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MessageStatusEnum status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}