package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment commentDto2Entity(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());

        return comment;
    }

    public CommentDto entity2CommentDto(Comment comment){

        CommentDto commentDto = new CommentDto();

        commentDto.setBody(comment.getBody());
        commentDto.setId(comment.getId());

        return commentDto;

    }
}
