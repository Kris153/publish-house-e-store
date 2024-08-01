package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.UserRegisterDTO;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.repository.UserRepository;
import com.example.publish_house_online_shop.repository.UserRoleRepository;
import com.example.publish_house_online_shop.service.UserService;
import com.example.publish_house_online_shop.user.PublishHouseUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void register(UserRegisterDTO registerData) {
        UserEntity userToAdd = this.modelMapper.map(registerData, UserEntity.class);
        userToAdd.setPassword(this.passwordEncoder.encode(userToAdd.getPassword()));
        userToAdd.setRoles(List.of(this.userRoleRepository.findByRole(UserRoleEnum.USER).get()));
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
    @Override
    public Optional<UserEntity> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() instanceof PublishHouseUserDetails publishHouseUserDetails) {
            return this.userRepository.findByUsername(publishHouseUserDetails.getUsername());
        }
        return Optional.empty();
    }
}
