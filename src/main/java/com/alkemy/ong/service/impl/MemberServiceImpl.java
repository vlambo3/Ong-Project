package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.exception.ErrorSavingException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;
    private final MessageSource messageSource;
    private final MemberMapper mapper;

    @Override
    public MemberResponseDto create(MemberRequestDto dto) {
        Member memberSaved;
        try {
            Member member = mapper.memberDto2MemberEntity(dto);

            member.setCreationDate(LocalDateTime.now());
            member.setDeleted(false);

            /*
             * TODO: <- ImageService should validate and return the path of the File...
             * example:
             * member.setImage(imageService.getImage(dto.getImage()));
             */

            memberSaved = memberRepository.save(member);
        } catch (Exception e) {
            throw new ErrorSavingException(
                    messageSource.getMessage("error-saving", new Object[] { "the new Member: " }, Locale.US)
                    + e.getMessage());
        }

        return mapper.memberEntity2MemberDto(memberSaved);
    }

}
