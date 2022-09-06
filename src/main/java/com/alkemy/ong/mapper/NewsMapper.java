package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public News newsDto2NewsEntity(NewsDto dto) {
        News entity = new News();
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        return entity;
    }

    public NewsDto newsEntity2NewsDto(News entity) {
        NewsDto dto = new NewsDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        return dto;
    }
}
