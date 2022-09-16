package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewsService {

    NewsResponseDto getById(Long id);
    PageDto<NewsResponseDto> getPage(int pageNum);
    NewsResponseDto create(NewsRequestDto dto);
    void delete(Long id);
    NewsResponseDto update(NewsRequestDto news, Long id);

}
