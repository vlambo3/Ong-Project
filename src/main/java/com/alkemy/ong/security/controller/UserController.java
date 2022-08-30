package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserRequestDto user) throws Exception {
        return ResponseEntity.ok(service.save(user));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(service.authenticate(authRequest));
    }

}
