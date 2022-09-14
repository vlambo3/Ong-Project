package com.alkemy.ong.controller;

import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService service;

    @PostMapping
    public ResponseEntity<CommentResponseDto> addNewComment(@RequestBody @Valid CommentRequestDto dto){
        return ResponseEntity.status(CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@RequestBody @Valid CommentRequestDto dto,
                                                     @PathVariable Long id,
                                                     @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization){
        return ResponseEntity.ok(service.update(id,dto,authorization));
    }

}
