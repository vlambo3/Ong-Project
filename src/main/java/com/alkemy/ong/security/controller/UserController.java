package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
