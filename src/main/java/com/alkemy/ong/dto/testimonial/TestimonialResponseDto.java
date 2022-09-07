package com.alkemy.ong.dto.testimonial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialResponseDto {

    private Long id;
    private String name;
    private String image;
    private String content;
}
