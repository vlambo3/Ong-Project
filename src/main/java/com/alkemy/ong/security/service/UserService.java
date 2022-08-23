package com.alkemy.ong.security.service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.mapper.UserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto save(UserRequestDto dto) {
        User userCheck = userRepository.findByEmail(dto.getEmail());
        if(userCheck != null)
            throw new BadCredentialsException("Email is already in use");

        User newUser = userMapper.userRequestDto2UserEntity(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);
        return userMapper.userEntity2UserResponseDto(newUser);
    }

}
