package com.example.publish_house_online_shop.init;

import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.model.entities.UserRoleEntity;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.repository.UserRepository;
import com.example.publish_house_online_shop.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitAdminAndRoles implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
     private final PasswordEncoder passwordEncoder;

    public InitAdminAndRoles(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if(this.userRoleRepository.count() > 0){
            return;
        }
        this.userRoleRepository.saveAndFlush(new UserRoleEntity(UserRoleEnum.ADMIN));
        this.userRoleRepository.saveAndFlush(new UserRoleEntity(UserRoleEnum.USER));

        if(this.userRepository.count() > 0){
            return;
        }
        this.userRepository.saveAndFlush(new UserEntity(1,"Admin", "admin@example.com", passwordEncoder.encode("1234"),
                new ArrayList<>(List.of(this.userRoleRepository.findByRole(UserRoleEnum.ADMIN).get()))));
    }
}
