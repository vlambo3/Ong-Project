package com.alkemy.ong.service;

import com.alkemy.ong.dto.organization.OrganizationRequestDTO;
import com.alkemy.ong.dto.organization.OrganizationResponseDTO;

public interface IOrganizationService {

    OrganizationResponseDTO update(OrganizationRequestDTO dto);
    public OrganizationResponseDTO getPublicInfo();
}
