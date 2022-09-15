package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    private MessageSource messageSource;


    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.commentDto2Entity(commentRequestDto);
        Comment savedComment = commentRepository.save(comment);

        return commentMapper.entity2CommentDto(savedComment);

    }

    @Override
    public void delete(Authentication aut, Long id) {
        try {
            if (checkId(aut, id)) {
                Comment entity = commentRepository.getById(id);
                entity.setDeleted(true);
                commentRepository.save(entity);
            }
        }catch (Exception e){
            throw new NullPointerException(messageSource.getMessage("comment.not.found", null, Locale.US));
        }
    }

    private boolean checkId(Authentication auth, Long id) {
        String username = auth.getName();
        var commentEntityOptional = commentRepository.findById(id);
        if (commentEntityOptional.isPresent()) {
            Comment comment = commentEntityOptional.get();
            String emailUserCreator = comment.getUser().getEmail();
            String authorityUser = String.valueOf((long) auth.getAuthorities().size());
            return username.equals(emailUserCreator) || authorityUser.equals("ADMIN");
        } else return false;

    }

    //TODO to review as required
    // @Override
    public CommentResponseDto put(Long id, CommentRequestDto edit) {

        try {
            Comment savedComment = this.getCommentById(id);
            savedComment.setBody(edit.getBody());
            Comment editComment = commentRepository.save(savedComment);
            CommentResponseDto saveDto = commentMapper.entity2CommentDto(editComment);
            return saveDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    //TODO to review as required
    public Comment getCommentById(Long id) throws Exception {
        Optional<Comment> savedComment = commentRepository.findById(id);
        if(!savedComment.isPresent()){
            throw new Exception("invalid ID");
        }
        return savedComment.get();
    }

}
