package com.alkemy.ong.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO {
    @NotEmpty(message = "the name cannot be empty")
    private String name;
    @NotEmpty(message = "the content cannot be empty")
    private String content;
    @NotEmpty(message = "the image cannot be empty")
    private String image;
}
