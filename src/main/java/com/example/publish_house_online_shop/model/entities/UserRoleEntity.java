package com.example.publish_house_online_shop.model.entities;

import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles")
public class UserRoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity(UserRoleEnum role) {
        this.role = role;
    }

    public UserRoleEntity() {
    }

    public UserRoleEntity(Long id, UserRoleEnum role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return Objects.equals(id, that.id) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
