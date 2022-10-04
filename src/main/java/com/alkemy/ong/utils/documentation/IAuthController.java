package com.alkemy.ong.utils.documentation;

import com.alkemy.ong.security.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAuthController {
    @ApiOperation(value = "Save a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Resource is fetched successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR - Unable to save entity in the database.")
    })
    @PostMapping("/register")
    ResponseEntity<RegisterResponseDto> register(@Parameter(name = "User",description = "User data to save",required = true)
                                                 UserRequestDto user, BindingResult bindingResult);
    @ApiOperation(value = "Login", notes = "Login to authenticate in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the Email entered."),
            @ApiResponse(responseCode = "409", description = "CONFLICT - Email is already in use."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR.")
    })
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@Parameter(name = "Credentials",description = "User credentials",required = true)
                                                 AuthenticationRequest authRequest) throws Exception;
    @ApiOperation(value = "Update", notes = "Update user data by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Resource not found with the ID entered."),
            @ApiResponse(responseCode = "409", description = "CONFLICT - Email is already in use."),
            @ApiResponse(responseCode = "500", description = "INTERNAL ERROR.")
    })
    @PatchMapping("/users/{id}")
    ResponseEntity<UserResponseDto> update(@Parameter(name = "User",description = "User data to update",required = true)
                                           UserRequestDto user,
                                           @Parameter(description = "Id of user to update",required = true)
                                           Long id) throws Exception;

}
