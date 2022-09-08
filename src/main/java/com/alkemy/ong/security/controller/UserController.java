package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.UserDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getLoggerUserData(@RequestHeader(value = HttpHeaders.AUTHORIZATION,required = true) String authorization){
        return ResponseEntity.ok(service.getLoggerUserData(authorization));
    }
}
