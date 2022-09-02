package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDTO;

public interface IOrganizationService {

    OrganizationDto update(OrganizationDto organizationDto) throws Exception;
    public OrganizationPublicDTO getPublicInfo();
}
