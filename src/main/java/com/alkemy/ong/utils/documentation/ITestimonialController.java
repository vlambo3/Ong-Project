package com.alkemy.ong.utils.documentation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.*;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ITestimonialController {

    @ApiOperation(value = "Save a new Testimony", notes = "As an admin user, you can save a new testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged / User logged whitout ROLE_ADMIN"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    @ResponseStatus(CREATED)
    ResponseEntity<TestimonialResponseDto> addNewTestimonial(
            @Valid @RequestBody @Parameter(description = "Request DTO for create a new Testimonial") TestimonialRequestDto testimonial);

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
    ResponseEntity<TestimonialResponseDto> updateTestimonial(
            @Parameter(description = "Request DTO to update testimonial data") @Valid @RequestBody TestimonialRequestDto newTestimonial,
            @Parameter(description = "ID to find testimonial") @PathVariable Long id);

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
    ResponseEntity<Void> deleteTestimonial(@Parameter(description = "ID to find testimonial") @PathVariable Long id);

    @ApiOperation(value = "Get Testimonials info grouped by a maximum of ten pages", notes = "As an user, you can get a list of all testimonials by page, grouped by a maximum of ten pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Resource is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN - User not logged"),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to show, an error has occurred in the system.")
    })
    ResponseEntity<PageDto<TestimonialResponseDto>> getPage(@RequestParam @Parameter(description = "Number of page") int page);
}
