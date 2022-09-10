package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.organization.OrganizationRequestDTO;
import com.alkemy.ong.dto.organization.OrganizationResponseDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
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
    private final OrganizationMapper mapper;
    private final MessageSource messageSource;
    private final SlideServiceImpl slideService;

    @Transactional
    public OrganizationResponseDTO update(OrganizationRequestDTO dto) {
        if (repository.findAll().isEmpty()) {
            Organization organization = mapper.organizationDto2Entity(dto);
            organization.setCreationDate(LocalDateTime.now());
            organization.setUpdateDate(LocalDateTime.now());
            Organization saved = repository.save(organization);
            return mapper.organizationEntity2Dto(saved);
        } else {
            Organization organizationToUpdate = repository.findAll().get(0);
            Organization updated = mapper.updateOrganizationDto2Entity(dto, organizationToUpdate.getId());
            updated.setCreationDate(organizationToUpdate.getCreationDate());
            updated.setUpdateDate(LocalDateTime.now());
            return mapper.organizationEntity2Dto(repository.save(updated));
                   }
    }


    @Override
    public OrganizationResponseDTO getPublicInfo() {
        Optional<Organization> orgPublicInfo = repository.findAll().stream().findFirst();

        if (orgPublicInfo.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("not-found", null, Locale.US));
        }
        OrganizationResponseDTO organization = mapper.orgEntity2orgPublicDTO(orgPublicInfo);
        List<SlideResponseDTO> slides = slideService.findByOrganizationId(organization.getId());
        organization.setSlides(slides);
        return organization;
    }

}
