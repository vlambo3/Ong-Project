package com.alkemy.ong.security.service;

import com.alkemy.ong.security.dto.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(user.getUsername(), user.getPassword(), user.getRole());
    }

    public boolean checkIfUserExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public final String jwtToken (AuthenticationRequest authRequest) throws Exception {
        if(checkIfUserExist(userResponseDTO.getEmail())){
            throw new UsernameNotFoundException("There is no user with the requested email");
        }
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("ok: false", e);
        }
        return jwtTokenUtil.generateToken(userDetails);
    }
}
