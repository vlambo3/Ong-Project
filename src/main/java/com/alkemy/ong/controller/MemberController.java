package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.service.impl.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    
    @PostMapping
    public ResponseEntity<MemberResponseDto> addNewMember(@RequestBody @Valid MemberRequestDto dto){

        return ResponseEntity.status(CREATED).body(memberServiceImpl.create(dto));
    }
}
