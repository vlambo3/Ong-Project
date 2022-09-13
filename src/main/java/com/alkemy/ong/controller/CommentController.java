package com.alkemy.ong.controller;

import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
