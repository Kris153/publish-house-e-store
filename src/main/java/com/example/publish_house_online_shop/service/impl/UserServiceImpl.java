package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.UserDetailsDTO;
import com.example.publish_house_online_shop.model.dtos.UserRegisterDTO;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.model.entities.UserRoleEntity;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.repository.UserRepository;
import com.example.publish_house_online_shop.repository.UserRoleRepository;
import com.example.publish_house_online_shop.service.UserService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import com.example.publish_house_online_shop.service.exception.ObjectNotFoundException;
import com.example.publish_house_online_shop.user.PublishHouseUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<UserDetailsDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(UserServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public UserDetailsDTO getUserById(Integer userId) {
        Optional<UserEntity> userOpt = this.userRepository.findById(userId);
        if(userOpt.isEmpty()){
            throw new ObjectNotFoundException();
        }
        return map(userOpt.get());
    }

    @Override
    public boolean changeUserRoleById(Integer userId) {
        Optional<UserEntity> userOpt = this.userRepository.findById(userId);
        if(userOpt.isEmpty()){
            throw new BadRequestException();
        }
        UserEntity user = userOpt.get();
        if(user.getUsername().equals("Admin")){
            return false;
        }
        if(user.getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            List<UserRoleEntity> roleList = new ArrayList<>();
            roleList.add(this.userRoleRepository.findByRole(UserRoleEnum.ADMIN).get());
            user.setRoles(roleList);

        }else if(user.getRoles().get(0).getRole().equals(UserRoleEnum.ADMIN)){
            List<UserRoleEntity> roleList = new ArrayList<>();
            roleList.add(this.userRoleRepository.findByRole(UserRoleEnum.USER).get());
            user.setRoles(roleList);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials()
                    , authorities);
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
        this.userRepository.saveAndFlush(user);
        return true;
    }

    private static UserDetailsDTO map(UserEntity user){
        ModelMapper modelMapper = new ModelMapper();
        UserDetailsDTO mapped = modelMapper.map(user, UserDetailsDTO.class);
        String role = user.getRoles().get(0).getRole().toString();
        mapped.setRole(role);
        return mapped;
    }
}
