package com.alkemy.ong.controller;


import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.exception.CustomExceptionDetails;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.service.INewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/news")
@RestController
public class NewsController {

    private final INewsService service;
    private final ICommentService commentService;


    @Operation(summary = "Get New by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))}),
            @ApiResponse(responseCode = "404", description = "New not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageDto<NewsResponseDto>> getPage(@RequestParam int page) {
        PageDto<NewsResponseDto> pageDto = service.getPage(page);
        return ResponseEntity.ok(pageDto);
    }

    @Operation(summary = "Create New.")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "Created New.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Field cannot be empty.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))}),
            @ApiResponse(responseCode = "404", description = "New not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))})
    })
    @PostMapping
    public ResponseEntity<NewsResponseDto> createNew(@Valid @RequestBody NewsRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Delete new.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "New has been deleted.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))}),
            @ApiResponse(responseCode = "404", description = "New not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Update new by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated new.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))}),
            @ApiResponse(responseCode = "404", description = "New not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionDetails.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody NewsRequestDto news, @PathVariable Long id) {
        NewsResponseDto newResponse = service.update(news, id);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentBasicResponseDto>> getCommentsByNewsId(@Valid @PathVariable Long id) {
        List<CommentBasicResponseDto> comments = commentService.getAllCommentsByNewsId(id);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

}
