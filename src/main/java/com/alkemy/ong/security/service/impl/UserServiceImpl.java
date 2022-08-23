package com.alkemy.ong.security.service.impl;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.mapper.UserMapper;
import com.alkemy.ong.security.service.IUserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto save(UserRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if(user != null)
            throw new BadCredentialsException("Email is already in use");

        User savedUser = userRepository.save(userMapper.userRequestDto2UserEntity(dto));
        return userMapper.userEntity2UserResponseDto(savedUser);
    }

}
