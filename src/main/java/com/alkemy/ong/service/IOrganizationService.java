package com.alkemy.ong.service;

import com.alkemy.ong.dto.organization.OrganizationDto;
import com.alkemy.ong.dto.organization.OrganizationPublicDTO;

public interface IOrganizationService {

    OrganizationDto update(OrganizationDto organizationDto) throws Exception;
    public OrganizationPublicDTO getPublicInfo();
}
