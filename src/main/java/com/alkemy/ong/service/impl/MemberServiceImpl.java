package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;
import com.alkemy.ong.service.IMemberService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.UnableToSaveEntityException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository repository;
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

            memberSaved = repository.save(member);
        } catch (Exception e) {
            throw new UnableToSaveEntityException(
                    messageSource.getMessage("error-saving", new Object[]{"the new Member: "}, Locale.US)
                            + e.getMessage());
        }

        return mapper.memberEntity2MemberDto(memberSaved);
    }

    @Override
    public List<MemberResponseDto> findAll() {
        List<Member> members = repository.findAll();
        if (members.isEmpty()) {
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        }
        return mapper.allMembers2MembersDtos(members);
    }

    public MemberResponseDto update(MemberRequestDto dto, Long id) {
        Member entity = getMemberById(id);
        try {
            entity.setName(dto.getName());
            entity.setFacebookUrl(dto.getFacebookUrl());
            entity.setInstagramUrl(dto.getInstagramUrl());
            entity.setLinkedinUrl(dto.getLinkedinUrl());
            entity.setImage(dto.getImage());
            entity.setDescription(dto.getDescription());
            repository.save(entity);
            return mapper.memberEntity2MemberDto(entity);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-member", null, Locale.US));
        }
    }

    private Member getMemberById (Long id) {
        Optional<Member> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new NotFoundException(messageSource.getMessage("member-not-found", null ,Locale.US));
        return entity.get();
    }
}
