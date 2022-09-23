package com.alkemy.ong.dto.organization;

import com.alkemy.ong.dto.slide.SlideResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResponseDTO implements Serializable {

    private Long id;
    private String name;
    private String image;
    private String address;
    private String phone;
    private String email;
    private String welcomeText;
    private String aboutUs;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private List<SlideResponseDto> slides;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    
}
