package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.AddPromoCodeDTO;
import com.example.publish_house_online_shop.model.dtos.PromoCodeDetailsDTO;
import com.example.publish_house_online_shop.model.dtos.UsePromoCodeDTO;

import java.util.List;

public interface PromoCodeService {
    void addPromoCode(AddPromoCodeDTO addPromoCodeDTO);

    List<PromoCodeDetailsDTO> getAllPromoCodes();

    boolean doesPromoCodeExists(String name);

    void changeStatusById(Integer promoCodeId);

    void deleteById(Integer promoCodeId);

    boolean doesPromoCodeExists(UsePromoCodeDTO promoCodeData);

    boolean isPromoCodeActive(UsePromoCodeDTO promoCodeData);

    Integer getDiscountPercent(UsePromoCodeDTO promoCodeData);

    String getName(UsePromoCodeDTO promoCodeData);
}
