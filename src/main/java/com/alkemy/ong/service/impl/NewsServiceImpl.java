package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;
import com.alkemy.ong.mapper.GenericMapper;
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
    private final GenericMapper mapper;
    private final MessageSource messageSource;

    public NewsResponseDto getById(Long id) {
        if (id <= 0) {
            throw new ArithmeticException(messageSource.getMessage("error-negative-id", null, Locale.US));
        }
        News entity = getNewsById(id);
        return mapper.map(entity, NewsResponseDto.class);
    }

    private News getNewsById(Long id) {
        Optional<News> news = repository.findById(id);
        if(news.isEmpty()){
            throw new NotFoundException(messageSource.getMessage("news-not-found", null, Locale.US));
        }
        return news.get();
    }

    public NewsResponseDto create(NewsRequestDto dto) {
        List<News> news = repository.findAll();

        news.forEach(n -> {
            if(repository.findByName(n.getName()).equalsIgnoreCase(dto.getName())) {
                throw new AlreadyExistsException(
                        messageSource.getMessage("news-name-already-exists", null, Locale.US));
            }
        });
        News entity = mapper.map(dto, News.class);
        entity.setCreationDate(LocalDateTime.now());
        entity = repository.save(entity);
        return mapper.map(entity, NewsResponseDto.class);
    }

    public void delete (Long id) {
        News entity = getNewsById(id);
        entity.setUpdateDate(LocalDateTime.now());
        repository.deleteById(id);
    }

    public NewsResponseDto update(NewsRequestDto dto, Long id) {
        News entity = getNewsById(id);
        try {
            News updatedEntity = mapper.map(dto, News.class);
            updatedEntity.setId(entity.getId());
            updatedEntity.setCreationDate(entity.getCreationDate());
            updatedEntity.setUpdateDate(LocalDateTime.now());
            updatedEntity = repository.save(updatedEntity);
            return mapper.map(updatedEntity, NewsResponseDto.class);
        }catch (Exception e) {
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-news",null, Locale.US));
        }
    }

}
