package com.alkemy.ong.security.mapper;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    PasswordEncoder passwordEncoder;

    UserMapper (PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User userDto2UserEntity(UserDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setPhoto(dto.getPhoto());
        entity.setRoleId(dto.getRoleId());
        return entity;
    }

    public UserDto userEntity2UserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword("Protected");
        dto.setPhoto(entity.getPhoto());
        dto.setRoleId(entity.getRoleId());
        return dto;
    }

}
