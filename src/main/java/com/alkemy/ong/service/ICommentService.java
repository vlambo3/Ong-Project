package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.comment.CommentBodyResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;

public interface ICommentService {

    //TODO to review as required
    CommentResponseDto save(CommentRequestDto commentRequestDto);

    //TODO to review as required
    void delete(Long id);
    CommentResponseDto update(Long id, CommentRequestDto edit,String auth);

    List<CommentBodyResponseDto> getAllCommentBodies();


}
