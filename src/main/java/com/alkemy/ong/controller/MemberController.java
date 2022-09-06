package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.service.IMemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final IMemberService memberService;
    
    @PostMapping
    public ResponseEntity<MemberResponseDto> addNewMember(@RequestBody @Valid MemberRequestDto dto){

        return ResponseEntity.status(CREATED).body(memberService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll(){
        
        return ResponseEntity.status(OK).body(memberService.findAll());
    }
}
