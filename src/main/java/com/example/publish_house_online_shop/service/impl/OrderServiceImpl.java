package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.BookDetailsForCartDTO;
import com.example.publish_house_online_shop.model.dtos.OrderDetailsDTO;
import com.example.publish_house_online_shop.model.entities.BookEntity;
import com.example.publish_house_online_shop.model.entities.OrderEntity;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.model.enums.OrderStatusEnum;
import com.example.publish_house_online_shop.repository.OrderRepository;
import com.example.publish_house_online_shop.repository.UserRepository;
import com.example.publish_house_online_shop.service.OrderService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public List<OrderDetailsDTO> getAllOrders() {
        return this.orderRepository.findAll().stream().map(OrderServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public void changeOrderStatusToCompletedById(Integer orderId) {
        Optional<OrderEntity> orderOpt = this.orderRepository.findById(orderId);
        if(orderOpt.isEmpty()){
            throw new BadRequestException();
        }
        OrderEntity order = orderOpt.get();
        order.setStatus(OrderStatusEnum.COMPLETED);
        this.orderRepository.saveAndFlush(order);
    }
    @Transactional
    @Override
    public OrderDetailsDTO getOrderById(Integer orderId) {
        return getAllOrders().stream().filter(o -> o.getId().equals(orderId)).findFirst().orElseThrow();
    }
    @Transactional
    @Override
    public List<OrderDetailsDTO> getAllOrderByUserId(Integer userId) {
        Optional<UserEntity> userOpt = this.userRepository.findById(userId);
        if(userOpt.isEmpty()){
            throw new BadRequestException();
        }
        UserEntity user = userOpt.get();
        return getAllOrders().stream().filter(o -> o.getUsername().equals(user.getUsername())).collect(Collectors.toList());
    }

    private static OrderDetailsDTO map(OrderEntity order){
        ModelMapper modelMapper = new ModelMapper();
        OrderDetailsDTO toReturn = modelMapper.map(order, OrderDetailsDTO.class);
        toReturn.setUsername(order.getUser().getUsername());
        Map<BookDetailsForCartDTO, Integer> booksQuantityMap = new HashMap<>();
        for (BookEntity bookEntity : order.getBooks()) {
            BookDetailsForCartDTO mapped = modelMapper.map(bookEntity, BookDetailsForCartDTO.class);
            booksQuantityMap.putIfAbsent(mapped, 0);
            booksQuantityMap.put(mapped, booksQuantityMap.get(mapped) + 1);
        }
        toReturn.setBooksQuantitiesMap(booksQuantityMap);
        if(order.getPromoCode() == null){
            toReturn.setPromoCodeName(null);
            toReturn.setPromoCodeDiscountPercent(null);
        }else{
            toReturn.setPromoCodeName(order.getPromoCode().getName());
        }
        return toReturn;
    }
}
