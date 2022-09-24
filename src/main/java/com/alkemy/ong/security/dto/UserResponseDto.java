package com.alkemy.ong.security.dto;

import com.alkemy.ong.security.model.Role;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private Role role;

    private String token;

}
