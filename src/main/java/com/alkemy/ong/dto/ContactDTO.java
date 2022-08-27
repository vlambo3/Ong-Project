package com.alkemy.ong.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO implements Serializable {

    private Long id;

    private String phone;

    private String email;

    private String message;
}
