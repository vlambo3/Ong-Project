package com.alkemy.ong.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO {

    @NotBlank(message = "The name cannot be null")
    private String name;
    @NotBlank(message = "The content cannot be null")
    private String content;
    @NotBlank(message = "The image cannot be null")
    private String image;

}
