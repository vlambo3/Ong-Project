package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceIml implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = commentMapper.commentDto2Entity(commentDto);
        Comment savedComment = commentRepository.save(comment);
        CommentDto result = commentMapper.entity2CommentDto(savedComment);

        return result;
    }

    @Override
    public void delete(Long id) {
    commentRepository.deleteById(id);
    }

    @Override
    public CommentDto put(Long id, CommentDto edit) {

        try {
            Comment savedComment = this.getCommentById(id);
            savedComment.setBody(edit.getBody());
            Comment editComment = commentRepository.save(savedComment);
            CommentDto saveDto = commentMapper.entity2CommentDto(editComment);
            return saveDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public Comment getCommentById(Long id) throws Exception {
        Optional<Comment> savedComment = commentRepository.findById(id);
        if(!savedComment.isPresent()){
            throw new Exception("invalid ID");
        }
        return savedComment.get();
    }

}
