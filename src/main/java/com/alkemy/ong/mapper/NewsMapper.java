package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public News newsDto2NewsEntity(NewsRequestDto dto) {
        News entity = new News();
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setCategory(dto.getCategory());
        return entity;
    }

    public NewsResponseDto newsEntity2NewsDto(News entity) {
        NewsResponseDto dto = new NewsResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCategory(entity.getCategory());
        return dto;
    }
}
