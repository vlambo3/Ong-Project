package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.IdNullOrNegativeException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {



    private final CommentRepository repository;

    private final CommentMapper mapper;

    private final MessageSource messageSource;




    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
        Comment comment = mapper.commentDto2Entity(commentRequestDto);
        Comment savedComment = repository.save(comment);

        return mapper.entity2CommentDto(savedComment);

    }

    //TODO to review as required
    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }


    //TODO to review as required
    // @Override
    public CommentResponseDto put(Long id, CommentRequestDto edit) {

        try {
            Comment savedComment = this.getCommentById(id);
            savedComment.setBody(edit.getBody());
            Comment editComment = repository.save(savedComment);
            CommentResponseDto saveDto = mapper.entity2CommentDto(editComment);
            return saveDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<CommentBasicResponseDto> getAllCommentsByNewsId(Long newsId) {
        if(newsId == null || newsId < 0){
            throw new IdNullOrNegativeException(messageSource.getMessage("id-null-or-negative", null, Locale.US));
        }
        List<Comment> comments = repository.findByNewsId(newsId);
        if(comments.isEmpty()){
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        }
        return mapper.entityList2CommentDtoList(comments);
    }

    //TODO to review as required
    private Comment getCommentById(Long id) throws Exception {
        Optional<Comment> savedComment = repository.findById(id);
        if(savedComment.isEmpty()){
            throw new NotFoundException(messageSource.getMessage("comment-not-found", null ,Locale.US));
        }
        return savedComment.get();
    }



}
