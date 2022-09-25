package com.alkemy.ong.service;

import com.alkemy.ong.dto.organization.OrganizationRequestDto;
import com.alkemy.ong.dto.organization.OrganizationResponseDto;

public interface IOrganizationService {

    OrganizationResponseDto update(OrganizationRequestDto dto);
    public OrganizationResponseDto getPublicInfo();
}
