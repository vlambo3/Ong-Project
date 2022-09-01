package com.alkemy.ong.security.mapper;



import com.alkemy.ong.security.model.User;
import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userRequestDto2UserEntity(UserRequestDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoto(dto.getPhoto());
        entity.setRole(dto.getRole());
        return entity;
    }

    public UserResponseDto userEntity2UserResponseDto(User entity) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoto(entity.getPhoto());
        dto.setRole(entity.getRole());
        return dto;
    }

    public AuthenticationRequest userRequestDto2AuthenticationRequest(UserRequestDto dto){
        AuthenticationRequest authRequestDto = new AuthenticationRequest();
        authRequestDto.setEmail(dto.getEmail());
        authRequestDto.setPassword(dto.getPassword());
        return authRequestDto;
    }

}
