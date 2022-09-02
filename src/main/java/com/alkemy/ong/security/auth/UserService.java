package com.alkemy.ong.security.auth;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.security.model.User;
import com.alkemy.ong.security.repository.UserRepository;

import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.security.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticatorManager authenticatorManager;
    private final JwtUtils jwtUtils;
    private final CustomDetailsService userDetailsService;
    private final IEmailService emailService;


    private final MessageSource messageSource;
    public UserResponseDto save(UserRequestDto dto) {
      
        User userCheck = userRepository.findByEmail(dto.getEmail());
        if(userCheck != null)
            throw new BadCredentialsException("Email is already in use");

        User newUser = userMapper.userRequestDto2UserEntity(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);

        UserResponseDto userResponseDto = userMapper.userEntity2UserResponseDto(newUser);
        AuthenticationRequest authenticationRequest = userMapper.userRequestDto2AuthenticationRequest(dto);
        AuthenticationResponse token = authenticate(authenticationRequest);
        userResponseDto.setToken(token.getJwt());
        emailService.sendEmail(dto.getEmail());
        return userResponseDto;

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


    public UserResponseDto update(UserRequestDto updateDto, Long id){
        if (!userRepository.existsById(id)){
            throw new NotFoundException(messageSource.getMessage("not-found", new Object[]{"User"},Locale.US));
        }
        User userModified = userMapper.userRequestDto2UserEntity(updateDto);
        userModified.setId(id);
        userModified.setPassword(passwordEncoder.encode(userModified.getPassword()));
        return userMapper.userEntity2UserResponseDto(userRepository.save(userModified));
    }



}
