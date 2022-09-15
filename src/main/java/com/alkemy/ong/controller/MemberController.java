package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.service.IMemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final IMemberService service;

    @ApiOperation(value = "Add Member",notes = "save a new member")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "201",description = "Created",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<MemberResponseDto> addNewMember(@Parameter(name = "Member",description = "Member data to save",required = true)
                                                              @RequestBody @Valid MemberRequestDto dto){
        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @ApiOperation(value = "Find All",notes = "bring a list of members")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200",description = "Ok",
                    content = @Content(schema = @Schema(implementation = MemberResponseDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "500",description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll(){
        
        return ResponseEntity.status(OK).body(service.findAll());
    }

    @ApiOperation(value = "Update a member",notes = "update a member's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Ok",
            content = @Content(schema = @Schema(implementation = MemberResponseDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@Parameter(name = "Member",description = "Member data to update",required = true)
                                                              @Valid @RequestBody MemberRequestDto dto,
                                                          @Parameter(description = "Id of member to update",required = true)
                                                          @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto, id));
    }

    @ApiOperation(value = "Delete a Member",notes = "remove a member from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Ok"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Parameter(description = "Id of member to delete",required = true)@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
