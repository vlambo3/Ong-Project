package com.alkemy.ong.dto.category;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    @NotBlank(message = "Name can't be null or empty")
    private String name;
    
    private String description;

    private String image;

}

