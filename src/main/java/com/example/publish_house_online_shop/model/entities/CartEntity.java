package com.example.publish_house_online_shop.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    @JoinTable(name = "carts_books", joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<BookEntity> books;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @OneToOne
    private UserEntity user;
}