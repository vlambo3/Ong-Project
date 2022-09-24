package com.alkemy.ong.security.auth;

import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.NotLoggedUserException;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.security.dto.*;
import com.alkemy.ong.security.model.User;
import com.alkemy.ong.security.repository.RolesRepository;
import com.alkemy.ong.security.repository.UserRepository;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.IEmailService;

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

    private final GenericMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticatorManager authenticatorManager;
    private final JwtUtils jwtUtils;
    private final CustomDetailsService userDetailsService;
    private final IEmailService emailService;
    private final UserRepository repository;
    private final MessageSource messageSource;

    private final RolesRepository rolesRepository;
    public UserResponseDto save(UserRequestDto dto) {

        User userCheck = repository.findByEmail(dto.getEmail());
        if (userCheck != null)
            throw new AlreadyExistsException(messageSource.getMessage("email-already-exists", null, Locale.US));

        User newUser = mapper.map(dto, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(rolesRepository.getById(2L));
        newUser = repository.save(newUser);

        //UserResponseDto userResponseDto = mapper.map(newUser, UserResponseDto.class);

        /*AuthenticationRequest authenticationRequest = mapper.map(dto, AuthenticationRequest.class);
        AuthenticationResponse token = authenticate(authenticationRequest);
        userResponseDto.setToken(token.getJwt());*/

        /*UserResponseDto userResponseDto = userMapper.userEntity2UserResponseDto(newUser);
        AuthenticationRequest authenticationRequest = userMapper.userRequestDto2AuthenticationRequest(dto);
        AuthenticationResponse token = authenticate(authenticationRequest);
        userResponseDto.setToken(token.getJwt());*/

        UserResponseDto userResponseDto = mapper.map(newUser, UserResponseDto.class);
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(dto.getEmail(), dto.getPassword());
        AuthenticationResponse token = authenticate(authenticationRequest);
        userResponseDto.setToken(token.getJwt());

        //userResponseDto.setToken(tokenRegister(userResponseDto.getEmail(), dto.getPassword()));
        emailService.sendEmail(userResponseDto.getEmail());
        return userResponseDto;
    }

    public String tokenRegister(String email, String password) {
        final Authentication authentication = authenticatorManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        final String jwt = jwtUtils.generateToken(userDetails);
        return jwt;
    }

    public UserResponseDto login(AuthenticationRequest authRequest) throws Exception {
        User user = repository.findByEmail(authRequest.getEmail());
        if (user == null)
            return null;

        String decrypt = passwordEncoder.encode(authRequest.getPassword());
        if (!decrypt.equalsIgnoreCase(user.getPassword()))
            return null;

        return mapper.map(user, UserResponseDto.class);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest dto) {
        final Authentication authentication = authenticatorManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated()) {

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
            final String jwt = jwtUtils.generateToken(userDetails);

            return new AuthenticationResponse(jwt);
        } else {
            throw new NotFoundException(messageSource.getMessage("user-not-found", null, Locale.US));
        }
    }

    public UserResponseDto update(UserRequestDto dto, Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(messageSource.getMessage("user-not-found", null, Locale.US));
        }
        User userModified = mapper.map(dto, User.class);
        userModified.setId(id);
        userModified.setPassword(passwordEncoder.encode(userModified.getPassword()));
        userModified = repository.save(userModified);
        return mapper.map(userModified, UserResponseDto.class);
    }

    public UserResponseDto getLoggerUserData(String auth) {
        if(auth.trim().isEmpty()){
            throw new BadRequestException(messageSource.getMessage("invalid-token", null, Locale.US));
        }
        String jwt = auth.substring(7);
        User user = repository.findByEmail(jwtUtils.extractUsername(jwt));
        return mapper.map(user, UserResponseDto.class);
    }

    public List<UserDto> getAll() {
        List<User> list = repository.findAll();
        if (list.isEmpty())
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        return mapper.mapAll(list, UserDto.class);
    }

    public boolean delete(Long id) {
        String user = getById(id).getEmail();
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (user.equals(loggedUser)) {
            repository.deleteById(id);
            return true;
        } else
            throw new NotLoggedUserException(messageSource.getMessage("not-logged-user", null, Locale.US));
    }

    private User getById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("user-not-found", null, Locale.US));
        }
        return user.get();
    }
}
