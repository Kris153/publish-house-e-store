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
}