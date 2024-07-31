package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.OrderStatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private UserEntity user;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;
    @Column(nullable = false)
    private String shippingName;
    @Column(nullable = false)
    private String shippingEmail;
    @Column(nullable = false)
    private String shippingPhoneNumber;
    @Column(nullable = false)
    private String shippingAddress;
    @ManyToMany
    @JoinTable(name = "orders_books", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<BookEntity> books;
    @Column(nullable = false, name = "total_price")
    private Double totalPrice;
}
