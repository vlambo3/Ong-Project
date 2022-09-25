package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.organization.OrganizationRequestDto;
import com.alkemy.ong.dto.organization.OrganizationResponseDto;
import com.alkemy.ong.dto.organization.OrganizationBasicResponseDto;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    private final OrganizationRepository repository;
    private final GenericMapper mapper;

    @Transactional
    public OrganizationResponseDto update(OrganizationRequestDto dto) {
        if (repository.count() == 0) {
            Organization organization = mapper.map(dto, Organization.class);
            organization.setCreationDate(LocalDateTime.now());
            organization = repository.save(organization);
            return mapper.map(organization, OrganizationResponseDto.class);
        } else {
            Organization organization = repository.findAll().get(0);
            organization.setName(dto.getName());
            organization.setImage(dto.getImage());
            organization.setAddress(dto.getAddress());
            organization.setPhone(dto.getPhone());
            organization.setEmail(dto.getEmail());
            organization.setWelcomeText(dto.getWelcomeText());
            organization.setAboutUs(dto.getAboutUs());
            organization.setUpdateDate(LocalDateTime.now());
            repository.save(organization);
            return mapper.map(organization, OrganizationResponseDto.class);
        }
    }

    @Override
    public OrganizationBasicResponseDto getPublicInfo() {
        Organization organization = repository.findAll().get(0);
        return mapper.map(organization, OrganizationBasicResponseDto.class);
    }

}
