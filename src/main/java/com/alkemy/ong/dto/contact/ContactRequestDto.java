package com.alkemy.ong.dto.contact;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDto implements Serializable {

    @NotBlank(message = "Field name cannot be null")
    private String name;

    @Pattern(regexp = "^(\\+?\\d{1,3})?(\\d{10})$", message = "The number phone es invalid")
    @NotBlank(message = "Phone cannot be null")
    private String phone;

    @Email(regexp = "^[a-zA-Z]+((\\.|_)*[a-zA-Z0-9]+)*((\\.|_)[a-zA-Z0-9]+)*@[a-z]+\\.\\w\\w\\w(\\.\\w\\w)?$",
            message = "The email is invalid")
    @NotBlank(message = "Field email cannot be null")
    private String email;

    private String message;

}
