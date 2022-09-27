package com.alkemy.ong.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private String token;
}
