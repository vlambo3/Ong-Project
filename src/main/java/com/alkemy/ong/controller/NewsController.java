package com.alkemy.ong.controller;


import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.service.INewsService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/news")
@RestController
public class NewsController {

    private final INewsService service;

    private final ICommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageDto<NewsResponseDto>> getPage(@RequestParam int page) {
        PageDto<NewsResponseDto> pageDto = service.getPage(page);
        return ResponseEntity.ok(pageDto);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody NewsRequestDto news, @PathVariable Long id) {
        NewsResponseDto newResponse = service.update(news, id);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentBasicResponseDto>> getCommentsByNewsId(@Valid @PathVariable Long id){
        List<CommentBasicResponseDto> comments = commentService.getAllCommentsByNewsId(id);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }


}
