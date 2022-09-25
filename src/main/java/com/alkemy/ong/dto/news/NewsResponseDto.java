package com.alkemy.ong.dto.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDto {

    private Long id;
    private String name;
    private String content;
    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long categoryId;
}
