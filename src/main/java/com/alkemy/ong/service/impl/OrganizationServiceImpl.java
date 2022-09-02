package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository repository;
    @Autowired
    private OrganizationMapper mapper;
    @Autowired
    private MessageSource messageSource;

    public OrganizationDto update(OrganizationDto edited) throws Exception {
        try {
            Optional<Organization> exists = repository.findAll().stream().findFirst();
            if (!exists.isPresent()) {
                throw new NotFoundException(messageSource.getMessage("not-found", new Object[]{"Organization"}, Locale.US));
            }
            Organization old = exists.get();
            Organization organization = mapper.organizationDto2Entity(edited);
            organization.setId(old.getId());
            return mapper.organizationEntity2Dto(repository.save(organization));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public OrganizationPublicDTO getPublicInfo() {
        Optional<Organization> orgPublicInfo = repository.findAll().stream().findFirst();

        if (orgPublicInfo.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("organization.not-found", null, Locale.US));
        }
        return mapper.orgEntity2orgPublicDTO(orgPublicInfo);

    }
}
