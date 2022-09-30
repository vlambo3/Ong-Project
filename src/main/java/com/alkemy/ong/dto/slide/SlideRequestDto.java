package com.alkemy.ong.dto.slide;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideRequestDto {

    @NotBlank(message = "field imageUrl cannot be null")
    private String image;
    private String text;
    @Nullable
    private Integer position;

}