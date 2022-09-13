package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {


    private final CommentRepository repository;
    private final GenericMapper mapper;
    private final MessageSource messageSource;



    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
        Comment comment = mapper.map(commentRequestDto, Comment.class);
        Comment savedComment = repository.save(comment);

        return mapper.map(savedComment, CommentResponseDto.class);

    }

    //TODO to review as required
    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }


    //TODO to review as required
    // @Override
    public CommentResponseDto put(Long id, CommentRequestDto dto) {
        Comment entity = getCommentById(id);
        try {
            entity = mapper.map(dto, Comment.class);
            repository.save(entity);
            return mapper.map(entity, CommentResponseDto.class);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-entity", new Object[]{id}, Locale.US));
        }

    }
    //TODO to review as required
    private Comment getCommentById(Long id) {
        Optional<Comment> comment = repository.findById(id);
        if(comment.isEmpty()){
            throw new NotFoundException(messageSource.getMessage("not-found", new Object[]{id}, Locale.US));
        }
        return comment.get();
    }

}
