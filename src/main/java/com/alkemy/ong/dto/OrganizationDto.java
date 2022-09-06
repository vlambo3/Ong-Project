package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter @Setter
public class OrganizationDto implements Serializable {

    private String name;
    private String img;
    private String address;
    @Pattern(regexp = "^(\\+?\\d{1,3})?(\\d{10})$", message = "the number phone es invalid")
    private String phone;
    @Email(regexp = "^[a-zA-Z]+((\\.|_)*[a-zA-Z0-9]+)*((\\.|_)[a-zA-Z0-9]+)*@[a-z]+\\.\\w\\w\\w(\\.\\w\\w)?$", message = "the email is invalid")
    private String email;
    private String welcomeText;
    private String aboutUs;
}
