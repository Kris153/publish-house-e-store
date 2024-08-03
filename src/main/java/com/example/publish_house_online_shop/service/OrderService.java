package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.OrderDetailsDTO;

import java.util.List;

public interface OrderService {
    List<OrderDetailsDTO> getAllOrders();

    void changeOrderStatusToCompletedById(Integer orderId);

    OrderDetailsDTO getOrderById(Integer orderId);

    List<OrderDetailsDTO> getAllOrderByUserId(Integer userId);
}
