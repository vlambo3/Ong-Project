package com.alkemy.ong.controller;

import com.alkemy.ong.dto.comment.CommentBodyResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService service;

    @PostMapping
    public ResponseEntity<CommentResponseDto> addNewComment(@RequestBody @Valid CommentRequestDto dto){
        return ResponseEntity.status(CREATED).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<CommentBodyResponseDto>> getAllCommentBodies(){
        return ResponseEntity.status(OK).body(service.getAllCommentBodies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@RequestBody @Valid CommentRequestDto dto,
                                                     @PathVariable Long id,
                                                     @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization){
        return ResponseEntity.ok(service.update(id,dto,authorization));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(Authentication auth, @PathVariable Long id) {
        service.delete(auth, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}