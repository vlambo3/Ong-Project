package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment commentDto2Entity(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.set

        return generoEntity;
    }
}
