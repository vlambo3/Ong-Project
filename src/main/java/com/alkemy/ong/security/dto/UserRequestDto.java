package com.alkemy.ong.security.dto;

import com.alkemy.ong.security.model.Role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class UserRequestDto {
    @ApiModelProperty(notes = "User Name",example = "First name of User",required = true)
    @NotBlank(message = "first name cannot be null")
    private String firstName;

    @ApiModelProperty(notes = "User Lastname",example = "Lastname of User",required = true)
    @NotBlank(message = "last name cannot be null")
    private String lastName;

    @ApiModelProperty(notes = "Email",example = "username@domain.com",required = true)
    @NotBlank(message = "email cannot be null")
    @Email(message = "email must be a valid email address")
    private String email;

    @ApiModelProperty(notes = "Password",example = "*********",required = true)
    @NotBlank(message = "password cannot be null")
    private String password;

    @ApiModelProperty(notes = "Photo",example = "url photo",required = false)
    private String photo;

}
