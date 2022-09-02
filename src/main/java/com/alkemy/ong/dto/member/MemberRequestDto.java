package com.alkemy.ong.dto.member;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    
    @NotBlank(message = "Image may not be null or empty")
    private String name;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

    @NotBlank(message = "Image may not be null or empty")
    private String image;

    private String description;
}
