package com.alkemy.ong.security.auth;

import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.NotFoundException;

import com.alkemy.ong.security.dto.*;
import com.alkemy.ong.security.model.User;
import com.alkemy.ong.security.repository.UserRepository;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.security.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    private final UserRepository repository;
    private final MessageSource messageSource;

    public UserResponseDto save(UserRequestDto dto) {
      
        User userCheck = userRepository.findByEmail(dto.getEmail());
        if(userCheck != null)
            throw new AlreadyExistsException(messageSource.getMessage("already-exists", new Object[]{"Email"},Locale.US));

        User newUser = userMapper.userRequestDto2UserEntity(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);

        UserResponseDto userResponseDto = userMapper.userEntity2UserResponseDto(newUser);
        AuthenticationRequest authenticationRequest = userMapper.userRequestDto2AuthenticationRequest(dto);
        AuthenticationResponse token = authenticate(authenticationRequest);
        userResponseDto.setToken(token.getJwt());
        //emailService.sendEmail(dto.getEmail());
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
            throw new NotFoundException(messageSource.getMessage("not-found", new Object[]{"User"},Locale.US));
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
    
    public UserResponseDto getLoggerUserData(String auth){
        String jwt = auth.substring(7);
        User user = userRepository.findByEmail(jwtUtils.extractUsername(jwt));
        return userMapper.userEntity2UserResponseDto(user);
    }

    public List<UserDto> getAll() {
        List<User> list = userRepository.findAll();
        if (list.isEmpty())
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        return userMapper.userEntityList2UserDtoList(list);
    }

    public void delete(Long id) {
        User user = getById(id);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);
        if (jwtUtils.validateToken(jwt, userDetails)){
            repository.deleteById(id);
        } else {
            throw new NotFoundException(messageSource.getMessage("user-not-found", null, Locale.US));
        }
    }

    private User getById(Long id) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException(messageSource.getMessage("user-not-found", null, Locale.US));
        }
        return user.get();
    }
}
