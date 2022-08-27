package com.alkemy.ong.dto.config.service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.mapper.UserMapper;
import com.alkemy.ong.dto.config.service.jwt.CustomAuthenticatorManager;
import com.alkemy.ong.dto.config.service.jwt.CustomDetailsService;
import com.alkemy.ong.dto.config.service.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticatorManager authenticatorManager;
    private final JwtUtils jwtUtils;
    private final CustomDetailsService userDetailsService;

    public UserResponseDto save(UserRequestDto dto) {
        User userCheck = userRepository.findByEmail(dto.getEmail());
        if(userCheck != null)
            throw new BadCredentialsException("Email is already in use");

        User newUser = userMapper.userRequestDto2UserEntity(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);
        return userMapper.userEntity2UserResponseDto(newUser);
    }



    public UserResponseDto login (AuthenticationRequest authRequest) throws Exception {
        User user = userRepository.findByEmail(authRequest.getEmail());
        if(user == null)
            return null;

        String decrypt = passwordEncoder.encode(authRequest.getPassword());
        if(!decrypt.equalsIgnoreCase(user.getPassword()))
            return null;

        return userMapper.userEntity2UserResponseDto(user);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest dto){
        final Authentication authentication = authenticatorManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
            final String jwt = jwtUtils.generateToken(userDetails);

            return new AuthenticationResponse(jwt);
        } else{
            throw new RuntimeException("User not found, please check the data entered");
        }
    }



}
