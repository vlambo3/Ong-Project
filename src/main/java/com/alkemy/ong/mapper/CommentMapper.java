package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public Comment commentDto2Entity(CommentRequestDto commentRequestDto) {

        Comment entity = new Comment();
        entity.setUserId(commentRequestDto.getUser_id());
        entity.setBody(commentRequestDto.getBody());
        entity.setNewsId(commentRequestDto.getNews_id());

        return entity;
    }

    public CommentResponseDto entity2CommentDto(Comment comment){

        CommentResponseDto dto = new CommentResponseDto();

        dto.setId(comment.getId());
        dto.setUser_id(comment.getUserId());
        dto.setBody(comment.getBody());
        dto.setNews_id(comment.getNewsId());

        return dto;

    }

    public CommentBasicResponseDto entity2CommentBasicDto(Comment comment){

        CommentBasicResponseDto dto = new CommentBasicResponseDto();

        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setNewsId(comment.getNewsId());

        return dto;
    }

    public List<CommentBasicResponseDto> entityList2CommentDtoList(List<Comment> comments){
        return comments.stream()
                .map(this::entity2CommentBasicDto)
                .collect(Collectors.toList());
    }
}
