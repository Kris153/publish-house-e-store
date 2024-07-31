package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.UserRegisterDTO;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.repository.UserRepository;
import com.example.publish_house_online_shop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO registerData) {
        UserEntity userToAdd = this.modelMapper.map(registerData, UserEntity.class);
        userToAdd.setPassword(this.passwordEncoder.encode(userToAdd.getPassword()));
        this.userRepository.saveAndFlush(userToAdd);
    }

    @Override
    public boolean confirmPassword(UserRegisterDTO registerData) {
        return registerData.getPassword().equals(registerData.getConfirmPassword());
    }

    @Override
    public boolean doUsernameExists(UserRegisterDTO registerData) {
        return this.userRepository.existsUserEntityByUsername(registerData.getUsername());
    }

    @Override
    public boolean doEmailExists(UserRegisterDTO registerData) {
        return this.userRepository.existsUserEntityByEmail(registerData.getEmail());
    }
}
