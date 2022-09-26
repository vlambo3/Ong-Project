package com.alkemy.ong.security.controller.utils;

import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.dto.RegisterResponseDto;
import com.alkemy.ong.security.dto.UserRequestDto;


public class AuthUtilsTest {

    public static UserRequestDto generateRequestDto() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("User name");
        dto.setLastName("User lastName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        return dto;
    }

    public static RegisterResponseDto generateResponseDto() {
        RegisterResponseDto dto = new RegisterResponseDto();
        dto.setId(1L);
        dto.setLastName("lastName");
        dto.setFirstName("firstName");
        dto.setPhoto("photo");
        dto.setToken("token");
        dto.setEmail("email♠4email.com");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithNullFirstName() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("");
        dto.setLastName("User lastName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithBlankFirstName() {
        UserRequestDto dto = new UserRequestDto();
        dto.setLastName("User lastName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithNullLastName() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("fisrtName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithBlankLastName() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("fisrtName");
        dto.setLastName("");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithNullEmail() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("fisrtName");
        dto.setLastName("lastName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithBlankEmail() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("fisrtName");
        dto.setLastName("lastName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("password");
        dto.setEmail("");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithNullPassword() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("fisrtName");
        dto.setLastName("lastName");
        dto.setPhoto("photo.jpeg");
        return dto;
    }

    public static UserRequestDto generateRequestDtoWithBlankPassword() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("fisrtName");
        dto.setLastName("lastName");
        dto.setPhoto("photo.jpeg");
        dto.setPassword("");
        dto.setEmail("email@email.com");
        return dto;
    }

    public static AuthenticationRequest generateAuthRequest(){
        return new AuthenticationRequest("email@email.com", "password");
    }

    public static AuthenticationResponse generateAuthResponse(){
        return new AuthenticationResponse("token");
    }

    public static AuthenticationRequest generateRequestWhitNullEmail(){
        return new AuthenticationRequest(null,"password");
    }

    public static AuthenticationRequest generateRequestWhitBlankEmail(){
        return new AuthenticationRequest("","password");
    }

    public static AuthenticationRequest generateRequestWhitNullPassword(){
        return new AuthenticationRequest("email@email.com",null);
    }

    public static AuthenticationRequest generateRequestWhitBlankPassword(){
        return new AuthenticationRequest("email@email.com","");
    }
}
