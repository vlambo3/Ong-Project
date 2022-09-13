package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.organization.OrganizationRequestDTO;
import com.alkemy.ong.dto.organization.OrganizationResponseDTO;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    private final OrganizationRepository repository;
    private final GenericMapper mapper;
    private final MessageSource messageSource;
    private final SlideServiceImpl slideService;

    @Transactional
    public OrganizationResponseDTO update(OrganizationRequestDTO dto) {
        if (repository.count() == 0) {
            Organization organization = mapper.map(dto, Organization.class);
            organization.setCreationDate(LocalDateTime.now());
            organization = repository.save(organization);
            return mapper.map(organization, OrganizationResponseDTO.class);
        } else {
            Organization organization = repository.findAll().get(0);
            Organization updatedOrganization = mapper.map(dto, Organization.class);
            updatedOrganization.setId(organization.getId());
            updatedOrganization.setCreationDate(organization.getCreationDate());
            updatedOrganization.setUpdateDate(LocalDateTime.now());
            updatedOrganization = repository.save(organization);
            return mapper.map(updatedOrganization, OrganizationResponseDTO.class);
        }
    }


    @Override
    public OrganizationResponseDTO getPublicInfo() {
        Optional<Organization> orgPublicInfo = repository.findAll().stream().findFirst();
        if (orgPublicInfo.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("not-found", null, Locale.US));
        }
        OrganizationResponseDTO organization = mapper.map(orgPublicInfo.get(), OrganizationResponseDTO.class);
        List<SlideResponseDto> slides = slideService.findByOrganizationId(organization.getId());
        organization.setSlides(slides);
        return organization;
    }

}
