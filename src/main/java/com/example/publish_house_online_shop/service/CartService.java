package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.entities.UserEntity;

public interface CartService {
    void addToCartById(Integer bookId, UserEntity user);

    void removeFromCartById(Integer bookId, UserEntity user);
}
