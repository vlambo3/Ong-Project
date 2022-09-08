package com.alkemy.ong.dto.slide;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideFullResponseDto {
    
    private Long id;
    private String slideImage;
    private String text;
    private Integer position;
    private String message;
    private Long organizationId;
    private String organizationName;
    private String organizationImage;
    private String organizationAddress;
    private String organizationPhone;
    private String organizationEmail;
}
