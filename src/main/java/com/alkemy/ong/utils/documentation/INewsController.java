package com.alkemy.ong.utils.documentation;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface INewsController {

    @ApiOperation(value = "Save a new news.", notes = "As an admin user, you can save a new news.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = NewsResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    ResponseEntity<NewsResponseDto> addNewNews(@Valid @RequestBody @Parameter(description = "Request DTO for add a new News.") NewsRequestDto dto);


    @ApiOperation(value = "Get news by ID.", notes = "As an admin user, you can get a news by his ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The resource was found successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = NewsResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system.")
    })
    ResponseEntity<NewsResponseDto> getNewsById(@Parameter(description = "ID to find news.") @PathVariable Long id);


    @ApiOperation(value = "Get news info grouped by a maximum of ten pages.", notes = "As an user, you can get a list of all news by page, grouped by a maximum of ten pages.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Resource is fetched successfully."),
            //TODO change NewsResponseDto ---> PageDto
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system.")
    })
    ResponseEntity<PageDto<NewsResponseDto>> getPage(@RequestParam @Parameter(description = "Number of page.") int page);

    @ApiOperation(value = "Get the comments by news.", notes = "As an admin user, you can get a list of all comments by news.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Resource is fetched successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CommentBasicResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system.")
    })
    ResponseEntity<List<CommentBasicResponseDto>> getCommentsByNewsId(
            @Valid @Parameter(description = "News comments.") @PathVariable Long id);


    @ApiOperation(value = "Update news by ID.", notes = "As an admin user, you can update a news entering the ID and modifying the fields of the DTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The resource was found successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid ID."),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to update news.")
    })
    ResponseEntity<NewsResponseDto> updateNews(@Parameter(description = "ID to find news.") @PathVariable Long id,
                                           @Parameter(description = "Request DTO to update news data.") @Valid @RequestBody NewsRequestDto news);


    @ApiOperation(value = "Delete(soft) news by ID.", notes = "As an admin user, you can delete(soft) a news by his ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED - Resource has been successfully removed.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid ID."),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to delete news.")
    })
    ResponseEntity<Void> deleteNews(
            @Parameter(description = "ID to find news") @PathVariable Long id);

}
