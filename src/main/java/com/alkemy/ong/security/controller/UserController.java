package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.dto.UserDto;
import com.alkemy.ong.security.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final IUserService service;

    UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto user) throws Exception {
        UserDto savedUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
