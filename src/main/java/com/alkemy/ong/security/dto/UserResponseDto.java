package com.alkemy.ong.security.dto;

import lombok.Getter;
import lombok.Setter;
import com.alkemy.ong.model.Role;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private Role role;

}
