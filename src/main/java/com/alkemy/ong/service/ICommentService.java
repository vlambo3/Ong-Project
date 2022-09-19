package com.alkemy.ong.service;


import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import java.util.List;
import com.alkemy.ong.dto.comment.CommentBodyResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import org.springframework.security.core.Authentication;



public interface ICommentService {


    CommentResponseDto save(CommentRequestDto commentRequestDto);

    void delete(Authentication auth, Long id);
    CommentResponseDto update(Long id, CommentRequestDto edit,String auth);

    List<CommentBodyResponseDto> getAllCommentBodies();

    List<CommentBasicResponseDto> getAllCommentsByNewsId(Long id);


}
