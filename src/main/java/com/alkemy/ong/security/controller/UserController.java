package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.service.UserService;
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
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto user) throws Exception {
        UserResponseDto savedUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
