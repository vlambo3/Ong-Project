package com.alkemy.ong.utils.documentation;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface IMemberController {

    @ApiOperation(value = "Add Member", notes = "save a new member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<MemberResponseDto> addNewMember(@Parameter(name = "Member", description = "Member data to save", required = true)
                                                   @RequestBody @Valid MemberRequestDto dto);


    @ApiOperation(value = "Find All", notes = "bring a list of members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<MemberResponseDto>> findAll();


    ResponseEntity<PageDto<MemberResponseDto>> getPage(@RequestParam int page);


    @ApiOperation(value = "Update a member", notes = "update a member's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<MemberResponseDto> updateMember(@Parameter(name = "Member", description = "Member data to update", required = true)
                                                   @Valid @RequestBody MemberRequestDto dto,
                                                   @Parameter(description = "Id of member to update", required = true)
                                                   @PathVariable Long id);


    @ApiOperation(value = "Delete a Member", notes = "remove a member from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity delete(@Parameter(description = "Id of member to delete", required = true) @PathVariable Long id);
}
