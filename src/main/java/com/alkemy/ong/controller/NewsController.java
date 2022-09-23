package com.alkemy.ong.controller;


import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.INewsService;
import com.alkemy.ong.utils.documentation.INewsController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Api(value = "News controller", description = "This controller has a CRUD for news")
public class NewsController implements INewsController {

    private final INewsService service;
    private final ICommentService commentService;

    @PostMapping
    public ResponseEntity<NewsResponseDto> createNews(@Valid @RequestBody NewsRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageDto<NewsResponseDto>> getPage(@RequestParam int page) {
        PageDto<NewsResponseDto> pageDto = service.getPage(page);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentBasicResponseDto>> getCommentsByNewsId(@Valid @PathVariable Long id){
        List<CommentBasicResponseDto> comments = commentService.getAllCommentsByNewsId(id);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDto> updateNews(@Valid @PathVariable Long id, @RequestBody NewsRequestDto news) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(news, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
