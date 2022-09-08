package com.alkemy.ong.dto.news;

import com.alkemy.ong.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto implements Serializable {

    @NotBlank(message = "Name cannot be empty or null")
    private String name;
    @NotBlank(message = "Content cannot be empty or null")
    private String content;
    @NotBlank(message = "Image cannot be empty or null")
    private String image;

    private Long categoryId;
}
