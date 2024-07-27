package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private UserEntity user;
    @Enumerated(value = EnumType.STRING)
    @Column
    private OrderStatus status;

}
