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
        entity.setCategoryId(dto.getCategoryId());
        return entity;
    }

    public NewsResponseDto newsEntity2NewsDto(News entity) {
        NewsResponseDto dto = new NewsResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setCategoryId(entity.getCategoryId());
        return dto;
    }
}
