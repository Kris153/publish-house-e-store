package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.*;
import com.example.publish_house_online_shop.model.entities.*;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.repository.CartRepository;
import com.example.publish_house_online_shop.repository.PromoCodeRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.net.FileNameMap;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final CartRepository cartRepository;
    private final PromoCodeRepository promoCodeRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, CartRepository cartRepository, PromoCodeRepository promoCodeRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.cartRepository = cartRepository;
        this.promoCodeRepository = promoCodeRepository;
    }

    @Override
    public void register(UserRegisterDTO registerData) {
        UserEntity userToAdd = this.modelMapper.map(registerData, UserEntity.class);
        userToAdd.setPassword(this.passwordEncoder.encode(userToAdd.getPassword()));
        userToAdd.setRoles(List.of(this.userRoleRepository.findByRole(UserRoleEnum.USER).get()));
        userToAdd.setOrders(new ArrayList<>());
        this.userRepository.saveAndFlush(userToAdd);
        CartEntity cartToAdd = new CartEntity();
        cartToAdd.setUser(this.userRepository.findByUsername(registerData.getUsername()).get());
        cartToAdd.setLastModified(Instant.now());
        this.cartRepository.saveAndFlush(cartToAdd);
//        Optional<UserEntity> userOpt = this.userRepository.findByUsername(registerData.getUsername());
//        if(userOpt.isEmpty()){
//            throw new BadRequestException();
//        }
//        UserEntity user = userOpt.get();
//        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(user);
//        if(cartOpt.isEmpty()){
//            throw new BadRequestException();
//        }
//        user.setCart(cartOpt.get());
//        this.userRepository.saveAndFlush(user);
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
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() instanceof PublishHouseUserDetails publishHouseUserDetails) {
            Optional<UserEntity> byUsername = this.userRepository.findByUsername(publishHouseUserDetails.getUsername());
            if(byUsername.isEmpty()){
                throw new ObjectNotFoundException();
            }
            return byUsername.get();
        }
        return null;
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
            if(getCurrentUser() != null){
                if(user.getUsername().equals(getCurrentUser().getUsername())){
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                    Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials()
                            , authorities);
                    SecurityContextHolder.getContext().setAuthentication(newAuthentication);
                }
            }
        }
        this.userRepository.saveAndFlush(user);
        return true;
    }
    @Transactional
    @Override
    public CartDetailsDTO getCurrentCart() {
        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(this.getCurrentUser());
        if(cartOpt.isEmpty()){
            throw new BadRequestException();
        }
        CartEntity cart = cartOpt.get();
        CartDetailsDTO toReturn = new CartDetailsDTO();
        toReturn.setTotalPrice(cart.getTotalPrice());
        Map<BookDetailsForCartDTO, Integer> map = new HashMap<>();
        for (BookEntity bookEntity : cart.getBooks()) {
            BookDetailsForCartDTO mapped = this.modelMapper.map(bookEntity, BookDetailsForCartDTO.class);
            map.putIfAbsent(mapped, 0);
            map.put(mapped, map.get(mapped) + 1);
        }
        toReturn.setBooksQuantitiesMap(map);
        if(cart.getPromoCode() == null){
            toReturn.setPromoCodeName(null);
        }else{
            toReturn.setPromoCodeName(cart.getPromoCode().getName());
        }
        cart.setLastModified(Instant.now());
        this.cartRepository.saveAndFlush(cart);
        return toReturn;
    }
    @Transactional
    @Override
    public boolean isCurrentCartEmpty() {
        return getCurrentCart().getBooksQuantitiesMap().isEmpty();
    }
    @Transactional
    @Override
    public void usePromoCode(UsePromoCodeDTO promoCodeData) {
        Optional<PromoCodeEntity> promoCodeOpt = this.promoCodeRepository.findByName(promoCodeData.getName());
        if(promoCodeOpt.isEmpty()){
            throw new BadRequestException();
        }
        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(this.getCurrentUser());
        if(cartOpt.isEmpty()){
            throw new BadRequestException();
        }
        CartEntity cart = cartOpt.get();
        cart.setPromoCode(null);
        cart.updateTotalPrice();
        cart.setPromoCode(promoCodeOpt.get());
        cart.updateTotalPrice();
        this.cartRepository.saveAndFlush(cart);
    }
    @Transactional
    @Override
    public void removePromoCode() {
        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(this.getCurrentUser());
        if(cartOpt.isEmpty()){
            throw new BadRequestException();
        }
        CartEntity cart = cartOpt.get();
        cart.setPromoCode(null);
        cart.updateTotalPrice();
        this.cartRepository.saveAndFlush(cart);
    }

    private static UserDetailsDTO map(UserEntity user){
        ModelMapper modelMapper = new ModelMapper();
        UserDetailsDTO mapped = modelMapper.map(user, UserDetailsDTO.class);
        String role = user.getRoles().get(0).getRole().toString();
        mapped.setRole(role);
        return mapped;
    }
}
