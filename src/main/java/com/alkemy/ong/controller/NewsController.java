package com.alkemy.ong.controller;

import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.service.INewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/news")
@RestController
public class NewsController {

    private final INewsService service;

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<NewsResponseDto> createNew(@Valid @RequestBody NewsRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
