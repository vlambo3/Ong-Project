package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import org.springframework.data.domain.Pageable;

public interface IMemberService {
    
    MemberResponseDto create(MemberRequestDto dto);

    List<MemberResponseDto> findAll();

    PageDto<MemberResponseDto> getPage(int pageNum);

    MemberResponseDto update(MemberRequestDto dto, Long id);

    void delete(Long id);

}
