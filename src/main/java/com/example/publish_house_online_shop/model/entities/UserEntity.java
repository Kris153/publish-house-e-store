package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles;
    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;
    @OneToOne(mappedBy = "user")
    private CartEntity cart;

    public UserEntity() {
        this.roles = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public UserEntity(Integer id, String username, String email, String password, List<UserRoleEntity> roles, List<OrderEntity> orders) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.orders = orders;
    }

    public UserEntity(Integer id, String username, String email, String password, List<UserRoleEntity> roles, List<OrderEntity> orders, CartEntity cart){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.orders = orders;
        this.cart = cart;
    }

    public UserEntity(String username, String email, String password, List<UserRoleEntity> roles, List<OrderEntity> orders, CartEntity cart) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.orders = orders;
        this.cart = cart;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }
}
