package com.alkemy.ong.dto.news;

import com.alkemy.ong.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDto {

    private Long id;
    private String name;
    private String content;
    private String image;
    private Category category;
}
