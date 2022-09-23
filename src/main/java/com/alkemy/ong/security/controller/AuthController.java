package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.dto.*;
import com.alkemy.ong.security.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;


   @PostMapping("/register")
   public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto user) throws Exception {
       return ResponseEntity.ok(service.save(user));
   }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(service.authenticate(authRequest));
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto user, @PathVariable Long id) throws Exception{
        UserResponseDto userResponseDto = service.update(user,id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

}
