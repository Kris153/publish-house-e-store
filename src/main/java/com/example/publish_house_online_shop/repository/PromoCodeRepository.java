package com.example.publish_house_online_shop.repository;

import com.example.publish_house_online_shop.model.entities.PromoCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCodeEntity, Integer> {
    Optional<PromoCodeEntity> findByName(String name);
}
