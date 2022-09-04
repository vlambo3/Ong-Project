package com.alkemy.ong.dto.slide;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideRequestDTO {

    private String imageUrl;
    private String text;
    private Integer position;

}
