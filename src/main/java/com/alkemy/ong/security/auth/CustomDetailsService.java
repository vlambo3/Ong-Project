package com.alkemy.ong.security.auth;

import com.alkemy.ong.enums.RoleEnum;
import com.alkemy.ong.security.model.User;
import com.alkemy.ong.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), singletonList(authority));
    }
}
