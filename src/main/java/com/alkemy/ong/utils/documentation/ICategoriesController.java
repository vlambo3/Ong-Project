package com.alkemy.ong.utils.documentation;

import com.alkemy.ong.dto.category.CategoryNameDto;
import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoriesController {

    @ApiOperation(value = "Save a new Category", notes = "As an admin user, you can save a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    ResponseEntity<CategoryResponseDto> addNewCategory(@Parameter(description = "Request DTO for add a new Category") CategoryRequestDto dto);


    @ApiOperation(value = "Get Category by ID", notes = "As an admin user, you can get a category by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Get all categories", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system..")
    })
    ResponseEntity<CategoryResponseDto> getById(@Parameter(description = "ID to find category") Long id);


    @ApiOperation(value = "Get all Categories", notes = "As an admin user, you can get a list of all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryNameDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system..")
    })
    ResponseEntity<List<CategoryNameDto>> getAll();


    @ApiOperation(value = "Get Categories info grouped by a maximum of ten pages", notes = "As an user, you can get a list of all categories by page, grouped by a maximum of ten pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Resource is fetched successfully", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system.")
    })
    ResponseEntity<?> getPage(@Parameter(description = "Number of page") int page);


    @ApiOperation(value = "Update categories by ID", notes = "As an admin user, you can update a category entering the ID and modifying the fields of the DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The resource was found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid ID"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to update categories.")
    })
    ResponseEntity<CategoryResponseDto> updateCategory(@Parameter(description = "ID to find category") Long id,
                                                       @Parameter(description = "Request DTO to update category data") CategoryRequestDto dto);


    @ApiOperation(value = "Delete(soft) category by Id", notes = "As an admin user, you can delete(soft) a category by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED - Resource has been successfully removed", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid ID"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to delete categories")
    })
    ResponseEntity<Void> deleteCategory(
            @Parameter(description = "ID to find category") Long id);


}
