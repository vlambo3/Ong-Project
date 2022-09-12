package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

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
    
    @PostMapping
    public ResponseEntity<MemberResponseDto> addNewMember(@RequestBody @Valid MemberRequestDto dto){

        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll(){
        
        return ResponseEntity.status(OK).body(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@Valid @RequestBody MemberRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto, id));
    }
}
