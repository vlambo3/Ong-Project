package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.alkemy.ong.exception.*;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.service.IMemberService;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository repository;
    private final MessageSource messageSource;
    private final GenericMapper mapper;

    @Override
    public MemberResponseDto create(MemberRequestDto dto) {
        Member memberSaved;
        try {
            Member member = mapper.map(dto, Member.class);

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

        return mapper.map(memberSaved, MemberResponseDto.class);
    }

    @Override
    public List<MemberResponseDto> findAll() {
        List<Member> members = repository.findAll();
        if (members.isEmpty()){
            throw new EmptyListException(messageSource.getMessage("empty-list",null ,Locale.US));
        }
        return mapper.mapAll(members, MemberResponseDto.class);
    }

    public MemberResponseDto update(MemberRequestDto dto, Long id) {
        Member entity = getMemberById(id);
        try {
            Member updatedEntity = mapper.map(dto, Member.class);
            updatedEntity.setId(entity.getId());
            updatedEntity.setCreationDate(entity.getCreationDate());
            updatedEntity.setUpdateDate(LocalDateTime.now());
            repository.save(updatedEntity);
            return mapper.map(updatedEntity, MemberResponseDto.class);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-member", null, Locale.US));
        }
    }

    public void delete(Long id) {
        Member entity = getMemberById(id);
        try {
            entity.setUpdateDate(LocalDateTime.now());
            repository.deleteById(id);
        } catch (Exception e) {
            throw new UnableToDeleteEntityException(messageSource.getMessage("unable-to-delete-entity", new Object[] { "Member", id }, Locale.US));
        }
    }

    private Member getMemberById(Long id) {
        Optional<Member> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new NotFoundException(messageSource.getMessage("not-found",new Object[] { "Member with id " + id } ,Locale.US));
        return entity.get();
    }

}
