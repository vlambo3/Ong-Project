package com.alkemy.ong.service;

import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import org.springframework.security.core.Authentication;

public interface ICommentService {

    //TODO to review as required
    CommentResponseDto save(CommentRequestDto commentRequestDto);

     void delete(Authentication auth, Long id);

    //TODO to review as required
    CommentResponseDto put(Long id, CommentRequestDto edit);


}
