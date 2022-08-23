package com.alkemy.ong.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private Long roleId;
}
