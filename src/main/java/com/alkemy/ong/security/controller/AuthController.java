package com.alkemy.ong.security.controller;

import com.alkemy.ong.security.dto.*;
import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.utils.documentation.IAuthController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements IAuthController {

    private final UserService service;


   @PostMapping("/register")
   public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody UserRequestDto user, BindingResult bindingResult) {
           /* EXAMPLE FOR DEMO
           if (bindingResult.hasErrors())
                throw new BadRequestException("Blank properties are not allowed");
           return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));*/
       return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
   }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(service.authenticate(authRequest));
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto user, @PathVariable Long id) throws Exception{
        UserResponseDto userResponseDto = service.update(user,id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

}
