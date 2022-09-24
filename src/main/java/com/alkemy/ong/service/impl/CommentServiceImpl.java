package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.comment.CommentRequestDto;
import com.alkemy.ong.dto.comment.CommentResponseDto;
import com.alkemy.ong.exception.*;
import com.alkemy.ong.dto.comment.CommentBodyResponseDto;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.ICommentService;

import java.time.LocalDateTime;
import java.util.List;
import com.alkemy.ong.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository repository;
    private final GenericMapper mapper;
    private final MessageSource messageSource;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
        Comment comment = mapper.map(commentRequestDto, Comment.class);
        Comment savedComment = repository.save(comment);
        return mapper.map(savedComment, CommentResponseDto.class);
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
        return mapper.mapAll(comments, CommentBasicResponseDto.class);
    }

    @Override
    public List<CommentBodyResponseDto> getAllCommentBodies() {
        List<Comment> bodies = repository.findAllByOrderByNewsCreationDateAsc();
        if(bodies.isEmpty()){
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        }
        return mapper.mapAll(bodies, CommentBodyResponseDto.class);
    }

    @Override
    public CommentResponseDto update(Long id, CommentRequestDto edit,String auth) {
        Comment comment = getCommentById(id);
        UserResponseDto user = userService.getLoggerUserData(auth);
        String userRole = user.getRole().getName().name();
        if (userRole != "ADMIN" && user.getId() != comment.getUserId())
            throw new ForbiddenException(messageSource.getMessage("forbidden",null,Locale.US));
        try{
            comment.setBody(edit.getBody());
            comment.setUserId(edit.getUserId());
            comment.setNewsId(edit.getNewsId());
            comment.setUpdateDate(LocalDateTime.now());
            repository.save(comment);
            return mapper.map(comment, CommentResponseDto.class);
        }catch (Exception e){
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-comment",
                    new Object[] {id}, Locale.US));
        }
    }

    @Override
    public void delete(Authentication aut, Long id) {
        Comment comment = getCommentById(id);
        try {
            comment.setUpdateDate(LocalDateTime.now());
            repository.deleteById(id);
        }catch (Exception e){
            throw new UnableToDeleteEntityException(messageSource.getMessage("unable-to-delete-comment", new Object[] {id}, Locale.US));
        }
    }

    private Comment getCommentById(Long id) {
        Optional<Comment> comment = repository.findById(id);
        if(comment.isEmpty())
            throw new NotFoundException(messageSource.getMessage("comment-not-found", new Object[] {id}, Locale.US));
        return comment.get();
    }

    private boolean checkId(Authentication auth, Long id) {
        String username = auth.getName();
        var commentEntityOptional = repository.findById(id);
        if (commentEntityOptional.isPresent()) {
            Comment comment = commentEntityOptional.get();
            String emailUserCreator = comment.getUser().getEmail();
            String authorityUser = String.valueOf((long) auth.getAuthorities().size());
            return username.equals(emailUserCreator) || authorityUser.equals("ADMIN");
        } else return false;
    }

}
