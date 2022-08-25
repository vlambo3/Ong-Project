package com.alkemy.ong.security.service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
<<<<<<< HEAD
import com.alkemy.ong.security.dto.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    private UserResponseDTO userResponseDTO;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(user.getEmail(), user.getPassword(), user.getRole());
    }

    public boolean checkIfUserExist(String username) {
        return userRepository.findByEmail(username) != null;
    }

    public final String jwtToken (AuthenticationRequest authRequest) throws Exception {
        if(checkIfUserExist(userResponseDTO.getEmail())){
            throw new UsernameNotFoundException("There is no user with the requested email");
        }
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("ok: false", e);
        }
        return jwtTokenUtil.generateToken(userDetails);
    }
=======
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(UserRequestDto dto) {
        User userCheck = userRepository.findByEmail(dto.getEmail());
        if(userCheck != null)
            throw new BadCredentialsException("Email is already in use");

        User newUser = userMapper.userRequestDto2UserEntity(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);
        return userMapper.userEntity2UserResponseDto(newUser);
    }

>>>>>>> develop
}
