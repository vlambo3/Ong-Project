package com.alkemy.ong.service;

import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;

import java.util.List;

public interface ICommentService {

    //TODO to review as required
    CommentResponseDto save(CommentRequestDto commentRequestDto);

    //TODO to review as required
    void delete(Long id);

    //TODO to review as required
    CommentResponseDto put(Long id, CommentRequestDto edit);

    List<CommentBasicResponseDto> getAllCommentsByNewsId(Long id);


}
