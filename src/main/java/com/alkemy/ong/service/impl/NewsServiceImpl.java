package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.INewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements INewsService {

    private final NewsRepository repository;
    private final NewsMapper mapper;
    private final MessageSource messageSource;

    public NewsResponseDto getById(Long id) {
        if (id <= 0) {
            throw new ArithmeticException(messageSource.getMessage("error-negative-id", null, Locale.US));
        }
        News entity = getNewsById(id);
        return mapper.newsEntity2NewsDto(entity);
    }

    private News getNewsById(Long id) {
        Optional<News> news = repository.findById(id);
        if(news.isEmpty()){
            throw new NotFoundException(messageSource.getMessage("news.not-found", null, Locale.US));
        }
        return news.get();
    }

    public NewsResponseDto create(NewsRequestDto dto) {
        List<News> news = repository.findAll();

        news.forEach(n -> {
            if(repository.findByName(n.getName()).equalsIgnoreCase(dto.getName())) {
                throw new AlreadyExistsException(
                        messageSource.getMessage("already-exists", new Object[] { "Category name" }, Locale.US));
            }
        });

        News entity = mapper.newsDto2NewsEntity(dto);
        entity.setCreationDate(LocalDateTime.now());
        entity.setUpdateDate(LocalDateTime.now());
        return mapper.newsEntity2NewsDto(repository.save(entity));
    }
}
