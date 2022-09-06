package com.alkemy.ong.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String message;
}
