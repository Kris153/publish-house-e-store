package com.example.publish_house_online_shop.service.scheduling;

import com.example.publish_house_online_shop.model.entities.CartEntity;
import com.example.publish_house_online_shop.repository.CartRepository;
import com.example.publish_house_online_shop.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.Optional;

@Component
public class CartScheduler {
    private final CartRepository cartRepository;

    public CartScheduler(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    //Every day
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void clearCarts(){
        for (CartEntity cart : this.cartRepository.findAll()) {
            if(!cart.isEmpty()){
                Duration durationBetween = Duration.between(cart.getLastModified(), Instant.now());
                long days = durationBetween.toDays();
                if(days >= 30){
                    cart.clearCart();
                    this.cartRepository.saveAndFlush(cart);
                }
            }
        }
    }
//    //Every minute
//    @Transactional
//    @Scheduled(cron = "0 * * * * *")
//    public void clearCarts(){
//        System.out.println("TEST");
//        for (CartEntity cart : this.cartRepository.findAll()) {
//            if(!cart.isEmpty()){
//                Duration durationBetween = Duration.between(cart.getLastModified(), Instant.now());
//                long minutes = durationBetween.toMinutes();
//                if(minutes >= 5){
//                    cart.clearCart();
//                    this.cartRepository.saveAndFlush(cart);
//                }
//            }
//        }
//    }
}
