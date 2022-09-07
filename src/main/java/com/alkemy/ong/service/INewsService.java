package com.alkemy.ong.service;

import com.alkemy.ong.dto.news.NewsResponseDto;

public interface INewsService {

    NewsResponseDto getById(Long id);
}
