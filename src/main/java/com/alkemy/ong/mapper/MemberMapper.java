package com.alkemy.ong.mapper;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.model.Member;

@Component
public class MemberMapper {
    
    public Member memberDto2MemberEntity(MemberRequestDto dto) {
        Member entity = new Member();
        
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setFacebookUrl(dto.getFacebookUrl());
        entity.setInstagramUrl(dto.getInstagramUrl());
        entity.setLinkedinUrl(dto.getLinkedinUrl());
        entity.setImage(dto.getImage());

        return entity;
    }

    public MemberResponseDto memberEntity2MemberDto(Member entity) {        
       MemberResponseDto dto = new MemberResponseDto();

       dto.setId(entity.getId());
       dto.setName(entity.getName());
       dto.setDescription(entity.getDescription());
       dto.setCreationDate(entity.getCreationDate());
       dto.setFacebookUrl(entity.getFacebookUrl());
       dto.setInstagramUrl(entity.getInstagramUrl());
       dto.setLinkedinUrl(entity.getLinkedinUrl());
       dto.setDeleted(entity.getDeleted());
       dto.setImage(entity.getImage());

       return dto;
    }

    public List<MemberResponseDto> allMembers2MembersDtos(List<Member> members){
        return members.stream()
                      .map(m -> memberEntity2MemberDto(m))
                      .collect(toList());
    }
}
