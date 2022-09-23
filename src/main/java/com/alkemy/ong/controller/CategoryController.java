package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;


import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.category.CategoryNameDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.service.ICategoryService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Api(value = "Category controller", description = "This controller has a CRUD for categories")
public class CategoryController {

    private final ICategoryService service;


    @ApiOperation(value = "Save a new Category", notes = "As an admin user, you can save a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    @PostMapping
    public ResponseEntity<CategoryResponseDto> addNewCategory(
            @RequestBody @Valid @Parameter(description = "Request DTO for add a new Category") CategoryRequestDto dto){
        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @ApiOperation(value = "Get all Categories", notes = "As an admin user, you can get a list of all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryNameDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    @GetMapping("/all")
    public ResponseEntity<List<CategoryNameDto>> getAll() {
        List<CategoryNameDto> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Get Category by ID", notes = "As an admin user, you can get a category by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(
            @Parameter(description = "ID to find category") @PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

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
    @DeleteMapping("/:{id}")
    public ResponseEntity delete(
            @Parameter(description = "ID to find category") @PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(
            @Parameter(description = "ID to find category") @PathVariable Long id,
            @Parameter(description = "Request DTO to update category data") @RequestBody @Valid CategoryRequestDto dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<PageDto<CategoryResponseDto>> getPage(@RequestParam int page) {
        PageDto<CategoryResponseDto> pageDto = service.getPage(page);
        return ResponseEntity.ok(pageDto);
    }

}
