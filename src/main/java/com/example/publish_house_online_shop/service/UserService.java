package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.UserRegisterDTO;

public interface UserService {
    void register(UserRegisterDTO registerData);

    boolean confirmPassword(UserRegisterDTO registerData);

    boolean doUsernameExists(UserRegisterDTO registerData);

    boolean doEmailExists(UserRegisterDTO registerData);
}
