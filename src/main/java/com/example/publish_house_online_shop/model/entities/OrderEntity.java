package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.OrderStatusEnum;
import jakarta.persistence.*;

import java.time.Instant;
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
    @Column(nullable = false, name = "created_date_time")
    private Instant createdDateTime;
    @ManyToOne
    private PromoCodeEntity promoCode;

    public OrderEntity() {
    }

    public OrderEntity(Integer id, UserEntity user, OrderStatusEnum status, String shippingName, String shippingEmail, String shippingPhoneNumber, String shippingAddress, List<BookEntity> books, Double totalPrice, Instant createdDateTime, PromoCodeEntity promoCode) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.shippingName = shippingName;
        this.shippingEmail = shippingEmail;
        this.shippingPhoneNumber = shippingPhoneNumber;
        this.shippingAddress = shippingAddress;
        this.books = books;
        this.totalPrice = totalPrice;
        this.createdDateTime = createdDateTime;
        this.promoCode = promoCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
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

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public PromoCodeEntity getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeEntity promoCode) {
        this.promoCode = promoCode;
    }
}
