package com.alkemy.ong.service;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;

public interface IMemberService {
    
    MemberResponseDto create(MemberRequestDto dto);
}
