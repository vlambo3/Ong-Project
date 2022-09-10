package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;

public interface IMemberService {
    
    MemberResponseDto create(MemberRequestDto dto);

    List<MemberResponseDto> findAll();

    void delete(Long id);
}
