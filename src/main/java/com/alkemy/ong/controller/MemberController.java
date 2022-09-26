package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import com.alkemy.ong.utils.documentation.IMemberController;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.alkemy.ong.dto.PageDto;
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
public class MemberController implements IMemberController {

    private final IMemberService service;


    @PostMapping
    public ResponseEntity<MemberResponseDto> addNewMember(MemberRequestDto dto){
        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberResponseDto>> findAll(){
        return ResponseEntity.status(OK).body(service.findAll());
    }
    @GetMapping
    public ResponseEntity<PageDto<MemberResponseDto>> getPage(int page) {
        PageDto<MemberResponseDto> pageDto = service.getPage(page);
        return ResponseEntity.ok(pageDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(MemberRequestDto dto, Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(Long id) {

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
