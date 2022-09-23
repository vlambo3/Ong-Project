package com.alkemy.ong.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequestDTO {
    @NotBlank(message = "The name cannot be empty or null")
    private String name;
    @NotBlank(message = "The image cannot be empty or null")
    private String image;
    private String address;
    private String phone;
    @Email(regexp = "^[a-zA-Z]+((\\.|_)*[a-zA-Z0-9]+)*((\\.|_)[a-zA-Z0-9]+)*@[a-z]+\\.\\w\\w\\w(\\.\\w\\w)?$", message = "the email is invalid")
    private String email;
    @NotBlank(message = "The welcome text cannot be empty or null")
    private String welcomeText;
    private String aboutUs;
    @Pattern(regexp = "^\\s*(http\\:\\/\\/)?facebook\\.com\\/[a-z\\d-_]{1,255}\\s*$/i", 
            message = "The url entered does not match the pattern")
    private String facebookUrl;
    @Pattern(regexp = "^\\s*(http\\:\\/\\/)?instagram\\.com\\/[a-z\\d-_]{1,255}\\s*$/i", 
            message = "The url entered does not match the pattern")
    private String instagramUrl;
    @Pattern(regexp = "^\\s*(http\\:\\/\\/)?linkedin\\.com\\/[a-z\\d-_]{1,255}\\s*$/i", 
            message = "The url entered does not match the pattern")
    private String linkedinUrl;
}
