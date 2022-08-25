package com.alkemy.ong.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequest {
    @NotNull(message = "Username cannot be null")
    @Email(message = "Username must be an email")
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min=7)
    private String password;
}
