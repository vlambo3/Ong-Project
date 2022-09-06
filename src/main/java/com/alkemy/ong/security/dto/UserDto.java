package com.alkemy.ong.security.dto;

import com.alkemy.ong.security.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private Role role;

}
