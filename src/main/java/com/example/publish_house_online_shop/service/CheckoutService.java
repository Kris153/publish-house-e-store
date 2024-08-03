package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.AddOrderDTO;
import com.example.publish_house_online_shop.model.dtos.CartDetailsDTO;
import com.example.publish_house_online_shop.model.entities.UserEntity;

public interface CheckoutService {
    void finishOrder(AddOrderDTO orderData, UserEntity currentUser, CartDetailsDTO currentCart);
}
