package com.example.publish_house_online_shop.repository;

import com.example.publish_house_online_shop.model.entities.CartEntity;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByUser(UserEntity user);
}
