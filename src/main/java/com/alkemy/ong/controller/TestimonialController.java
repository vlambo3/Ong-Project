package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.exception.CustomExceptionDetails;
import com.alkemy.ong.service.ITestimonialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/testimonials")
@RequiredArgsConstructor
@RestController
@Api(value = "Testimonial controller", description = "This controller has a CRUD for testimonials")
public class TestimonialController {

    private final ITestimonialService service;

    @ApiOperation(value = "Save a new Testimony", notes = "As an admin user, you can save a new testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<TestimonialResponseDto> save(
            @Valid @RequestBody @Parameter(description = "Request DTO for create a new Testimonial") TestimonialRequestDto testimonial) {
        TestimonialResponseDto savedTestimonial = service.save(testimonial);
        return ResponseEntity.status(CREATED).body(savedTestimonial);
    }

    @ApiOperation(value = "Update testimonial by ID", notes = "As an admin user, you can update an testimonial entering the ID and modifying the fields of the DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The resource was found successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid ID"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to update testimonial.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TestimonialResponseDto> update( 
        @Parameter(description = "Request DTO to update testimonial data") @Valid @RequestBody TestimonialRequestDto newTestimonial, 
        @Parameter(description = "ID to find testimonial") @PathVariable Long id) {
        TestimonialResponseDto updatedTestimonial = service.update(newTestimonial, id);
        return ResponseEntity.ok(updatedTestimonial);
    }

    @ApiOperation(value = "Delete(soft) testimonial by ID", notes = "As an admin user, you can delete(soft) an testimonial by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED - Resource has been successfully removed", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid ID"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to delete testimonial")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Parameter(description = "ID to find testimonial") @PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping
    public ResponseEntity<PageDto<TestimonialResponseDto>> getPage(@RequestParam int page) {
        PageDto<TestimonialResponseDto> testimonialDto = service.getPage(page);
        return ResponseEntity.ok(testimonialDto);
    }
}
