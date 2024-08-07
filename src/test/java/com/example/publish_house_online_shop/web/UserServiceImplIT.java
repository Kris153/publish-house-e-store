package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.model.entities.UserRoleEntity;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.repository.CartRepository;
import com.example.publish_house_online_shop.repository.UserRepository;
import com.example.publish_house_online_shop.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceImplIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "kris")
                        .param("email", "example@email.com")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        Optional<UserEntity> userEntityOpt = userRepository.findByUsername("kris");

        Assertions.assertTrue(userEntityOpt.isPresent());

        UserEntity userEntity = userEntityOpt.get();

        Assertions.assertEquals("kris", userEntity.getUsername());
        Assertions.assertEquals("example@email.com", userEntity.getEmail());
        Assertions.assertTrue(passwordEncoder.matches("password", userEntity.getPassword()));
        Assertions.assertEquals(new ArrayList<>(), userEntity.getOrders());
        Assertions.assertTrue(userRoleRepository.findByRole(UserRoleEnum.USER).isPresent());
        UserRoleEntity userRoleEntity = userRoleRepository.findByRole(UserRoleEnum.USER).get();
        Assertions.assertEquals(List.of(userRoleEntity), userEntity.getRoles());
        Assertions.assertTrue(cartRepository.findByUser(userEntity).isPresent());
    }
    @Test
    void testChangeUserRoleById() throws Exception {
        UserEntity userToTest = new UserEntity();
        userToTest.setUsername("kris");
        userToTest.setEmail("example@email.com");
        userToTest.setPassword(passwordEncoder.encode("password"));

        UserRoleEntity userRoleEntityUser = userRoleRepository.findByRole(UserRoleEnum.USER).get();
        UserRoleEntity userRoleEntityAdmin = userRoleRepository.findByRole(UserRoleEnum.ADMIN).get();

        userToTest.setRoles(List.of(userRoleEntityUser));
        userRepository.saveAndFlush(userToTest);
        UserEntity user = userRepository.findByUsername("kris").get();

        mockMvc.perform(patch("/profiles/change-role/{id}", user.getId())
                        .with(csrf())
                        .with(user("Admin").password("pass").roles("USER","ADMIN"))
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(String.format("/profiles/%s", user.getId())));

        UserEntity userAdmin = userRepository.findByUsername("kris").get();
        Assertions.assertTrue(userAdmin.getRoles().contains(userRoleEntityAdmin));
        Assertions.assertFalse(userAdmin.getRoles().contains(userRoleEntityUser));

        UserEntity currentUser = new UserEntity();
        currentUser.setUsername("Test");

        mockMvc.perform(patch("/profiles/change-role/{id}", user.getId())
                        .with(csrf())
                        .with(user("Admin").password("pass").roles("USER","ADMIN"))
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(String.format("/profiles/%s", user.getId())));

        UserEntity userUser = userRepository.findByUsername("kris").get();
        Assertions.assertTrue(userUser.getRoles().contains(userRoleEntityUser));
        Assertions.assertFalse(userUser.getRoles().contains(userRoleEntityAdmin));
    }
}
