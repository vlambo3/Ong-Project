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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities(user.getRole()));
    }

    public final String jwtToken (AuthenticationRequest authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        return jwtTokenUtil.generateToken(userDetails);
    }
}
