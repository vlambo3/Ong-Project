package com.alkemy.ong.dto.slide;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideRequestDto {

    private String image;
    private String text;
    @Nullable
    private Integer position;

}