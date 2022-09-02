package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDto {

    private long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String image;

    @NotBlank(message = "Content cannot be empty")
    private String content;
}
