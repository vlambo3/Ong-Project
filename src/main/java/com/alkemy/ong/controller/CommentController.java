package com.alkemy.ong.controller;

import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Locale;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImlp;
    private final MessageSource messageSource;


    @PostMapping
    public ResponseEntity<CommentResponseDto> addNewComment(@RequestBody @Valid CommentRequestDto dto){

        return ResponseEntity.status(CREATED).body(commentServiceImlp.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(Authentication auth, @PathVariable Long id){

        commentServiceImlp.existId(id);
        try {
            commentServiceImlp.delete(auth, id);
            return ResponseEntity.ok(messageSource.getMessage("comment.delete.successfully", null, Locale.US));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
