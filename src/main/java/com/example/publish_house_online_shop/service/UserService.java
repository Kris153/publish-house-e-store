package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.UserDetailsDTO;
import com.example.publish_house_online_shop.model.dtos.UserRegisterDTO;
import com.example.publish_house_online_shop.model.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(UserRegisterDTO registerData);

    boolean confirmPassword(UserRegisterDTO registerData);

    boolean doUsernameExists(UserRegisterDTO registerData);

    boolean doEmailExists(UserRegisterDTO registerData);
    Optional<UserEntity> getCurrentUser();

    List<UserDetailsDTO> getAllUsers();

    UserDetailsDTO getUserById(Integer userId);

    boolean changeUserRoleById(Integer userId);
}
