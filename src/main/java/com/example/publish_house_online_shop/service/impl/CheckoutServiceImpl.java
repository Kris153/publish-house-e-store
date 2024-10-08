package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddOrderDTO;
import com.example.publish_house_online_shop.model.dtos.BookDetailsForCartDTO;
import com.example.publish_house_online_shop.model.dtos.CartDetailsDTO;
import com.example.publish_house_online_shop.model.entities.*;
import com.example.publish_house_online_shop.model.enums.OrderStatusEnum;
import com.example.publish_house_online_shop.repository.*;
import com.example.publish_house_online_shop.service.CheckoutService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;
    private final PromoCodeRepository promoCodeRepository;
    private final ModelMapper modelMapper;

    public CheckoutServiceImpl(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository, CartRepository cartRepository, PromoCodeRepository promoCodeRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
        this.promoCodeRepository = promoCodeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void finishOrder(AddOrderDTO orderData, UserEntity currentUser, CartDetailsDTO currentCart) {
        OrderEntity orderToAdd = this.modelMapper.map(orderData, OrderEntity.class);
        Optional<UserEntity> userOpt = this.userRepository.findByUsername(currentUser.getUsername());
        if(userOpt.isEmpty()){
            throw new BadRequestException();
        }
        orderToAdd.setUser(userOpt.get());
        orderToAdd.setStatus(OrderStatusEnum.PROCESSING);
        orderToAdd.setCreatedDateTime(Instant.now());
        orderToAdd.setTotalPrice(currentCart.getTotalPrice());
        List<BookEntity> booksList = new ArrayList<>();
        for (Map.Entry<BookDetailsForCartDTO, Integer> entry : currentCart.getBooksQuantitiesMap().entrySet()) {
            Optional<BookEntity> bookOpt = this.bookRepository.findById(entry.getKey().getId());
            if(bookOpt.isEmpty()){
                throw new BadRequestException();
            }
            for (int i = 0; i < entry.getValue(); i++) {
                booksList.add(bookOpt.get());
            }
        }
        orderToAdd.setBooks(booksList);
        if(currentCart.getPromoCodeName() == null){
            orderToAdd.setPromoCodeName(null);
            orderToAdd.setPromoCodeDiscountPercent(null);
        }else{
            Optional<PromoCodeEntity> promoCodeOpt = this.promoCodeRepository.findByName(currentCart.getPromoCodeName());
            if(promoCodeOpt.isEmpty()){
                throw new BadRequestException();
            }
            orderToAdd.setPromoCodeName(promoCodeOpt.get().getName());
            orderToAdd.setPromoCodeDiscountPercent(promoCodeOpt.get().getDiscountPercent());
        }
        this.orderRepository.saveAndFlush(orderToAdd);
        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(currentUser);
        if(cartOpt.isEmpty()){
            throw new BadRequestException();
        }
        CartEntity cart = cartOpt.get();
        cart.setBooks(new ArrayList<>());
        cart.setPromoCode(null);
        cart.updateTotalPrice();
        this.cartRepository.saveAndFlush(cart);
    }
}
