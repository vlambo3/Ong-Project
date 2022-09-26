package com.alkemy.ong.security.dto;

import com.alkemy.ong.security.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "first name cannot be null")
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    private String lastName;

    @NotBlank(message = "email cannot be null")
    @Email(message = "email must be a valid email address")
    private String email;

    @NotBlank(message = "password cannot be null")
    private String password;

    private String photo;
}
