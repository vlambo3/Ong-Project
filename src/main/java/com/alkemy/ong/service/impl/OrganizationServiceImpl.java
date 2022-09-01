package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationPublicDTO;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private  OrganizationRepository organizationRepository;

    @Autowired

    private OrganizationMapper organizationMapper;




    @Override
    public OrganizationPublicDTO getPublicInfo() {
        Optional<Organization> orgPublicInfo = organizationRepository.findAll().stream().findFirst();

        if (orgPublicInfo.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("organization.not.found", null, Locale.ENGLISH));
        }
        return organizationMapper.orgEntity2orgPublicDTO(orgPublicInfo);
    }
}
