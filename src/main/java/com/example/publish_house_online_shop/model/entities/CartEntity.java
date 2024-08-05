package com.example.publish_house_online_shop.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    @ManyToOne
    private PromoCodeEntity promoCode;

    public CartEntity() {
        this.books = new ArrayList<>();
        this.totalPrice = 0d;
    }

    public CartEntity(Integer id, List<BookEntity> books, Double totalPrice, UserEntity user, PromoCodeEntity promoCode) {
        this.id = id;
        this.books = books;
        this.totalPrice = totalPrice;
        this.user = user;
        this.promoCode = promoCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    public void updateTotalPrice(){
        Double price = 0d;
        for (BookEntity book : this.books) {
            price += book.getPrice();
        }
        if(this.promoCode != null){
            price = price*(1-(double)promoCode.getDiscountPercent()/100);
        }
        this.setTotalPrice(price);
    }

    public PromoCodeEntity getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeEntity promoCode) {
        this.promoCode = promoCode;
    }

}