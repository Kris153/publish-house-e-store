package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddPromoCodeDTO;
import com.example.publish_house_online_shop.model.dtos.PromoCodeDetailsDTO;
import com.example.publish_house_online_shop.model.dtos.UsePromoCodeDTO;
import com.example.publish_house_online_shop.model.entities.PromoCodeEntity;
import com.example.publish_house_online_shop.model.enums.PromoCodeStatusEnum;
import com.example.publish_house_online_shop.repository.PromoCodeRepository;
import com.example.publish_house_online_shop.service.PromoCodeService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    private final PromoCodeRepository promoCodeRepository;
    private final ModelMapper modelMapper;

    public PromoCodeServiceImpl(PromoCodeRepository promoCodeRepository, ModelMapper modelMapper) {
        this.promoCodeRepository = promoCodeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPromoCode(AddPromoCodeDTO addPromoCodeDTO) {
        PromoCodeEntity promoCode = this.modelMapper.map(addPromoCodeDTO, PromoCodeEntity.class);
        promoCode.setStatus(PromoCodeStatusEnum.NOT_ACTIVE);
        this.promoCodeRepository.saveAndFlush(promoCode);
    }

    @Override
    public List<PromoCodeDetailsDTO> getAllPromoCodes() {
        return this.promoCodeRepository.findAll().stream().map(PromoCodeServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public boolean doesPromoCodeExists(String name) {
        return this.promoCodeRepository.findByName(name).isPresent();
    }

    @Override
    public void changeStatusById(Integer promoCodeId) {
        Optional<PromoCodeEntity> promoCodeOpt = this.promoCodeRepository.findById(promoCodeId);
        if(promoCodeOpt.isEmpty()){
            throw new BadRequestException();
        }
        PromoCodeEntity promoCode = promoCodeOpt.get();
        if(promoCode.getStatus().equals(PromoCodeStatusEnum.NOT_ACTIVE)){
            promoCode.setStatus(PromoCodeStatusEnum.ACTIVE);
        }else if(promoCode.getStatus().equals(PromoCodeStatusEnum.ACTIVE)){
            promoCode.setStatus(PromoCodeStatusEnum.NOT_ACTIVE);
        }
        this.promoCodeRepository.saveAndFlush(promoCode);
    }

    @Override
    public void deleteById(Integer promoCodeId) {
        this.promoCodeRepository.deleteById(promoCodeId);
    }

    @Override
    public boolean doesPromoCodeExists(UsePromoCodeDTO promoCodeData) {
        return doesPromoCodeExists(promoCodeData.getName());
    }

    @Override
    public boolean isPromoCodeActive(UsePromoCodeDTO promoCodeData) {
        return getPromoCode(promoCodeData).getStatus().equals(PromoCodeStatusEnum.ACTIVE);
    }

    @Override
    public Integer getDiscountPercent(UsePromoCodeDTO promoCodeData) {
        return getPromoCode(promoCodeData).getDiscountPercent();
    }

    @Override
    public String getName(UsePromoCodeDTO promoCodeData) {
        return getPromoCode(promoCodeData).getName();
    }

    private static PromoCodeDetailsDTO map(PromoCodeEntity promoCode){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(promoCode, PromoCodeDetailsDTO.class);
    }
    private PromoCodeEntity getPromoCode(UsePromoCodeDTO promoCodeData){
        Optional<PromoCodeEntity> promoCodeOpt = this.promoCodeRepository.findByName(promoCodeData.getName());
        if(promoCodeOpt.isEmpty()){
            throw new BadRequestException();
        }
        return promoCodeOpt.get();
    }
}
