package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationPublicDTO;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private  OrganizationRepository organizationRepository;

    @Autowired

    private OrganizationMapper organizationMapper;




    @Override
    public OrganizationPublicDTO getPublicInfo() {
        Organization orgPublicInfo = organizationRepository.findAll();
        return organizationMapper.orgEntity2orgPublicDTO(orgPublicInfo);
    }
}
