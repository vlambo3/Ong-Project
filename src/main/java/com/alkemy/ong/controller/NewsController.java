package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.INewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/news")
@RestController
public class NewsController {

    private final INewsService service;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getById(@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

}
