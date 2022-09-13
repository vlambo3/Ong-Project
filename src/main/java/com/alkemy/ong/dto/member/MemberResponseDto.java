package com.alkemy.ong.dto.member;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    
    private Long id;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;

}
