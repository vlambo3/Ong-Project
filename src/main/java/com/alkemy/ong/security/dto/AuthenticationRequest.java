package com.alkemy.ong.security.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @ApiModelProperty(notes = "Email",example = "username@domain.com",required = true)
    @NotBlank(message = "Username cannot be null")
    @Email(message = "Username must be an email")
    private String email;
    @ApiModelProperty(notes = "Password",example = "*********",required = true)
    @NotBlank(message = "Password cannot be null")
    @Size(min=7)
    private String password;
}
