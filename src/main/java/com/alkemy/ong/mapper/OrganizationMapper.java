package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.organization.OrganizationRequestDTO;
import com.alkemy.ong.dto.organization.OrganizationResponseDTO;
import com.alkemy.ong.model.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {

    private final SlideMapper slideMapper;

    public Organization organizationDto2Entity(OrganizationRequestDTO dto){
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setImage(dto.getImage());
        organization.setAddress(dto.getAddress());
        organization.setPhone(dto.getPhone());
        organization.setEmail(dto.getEmail());
        organization.setWelcomeText(dto.getWelcomeText());
        organization.setAboutUs(dto.getAboutUs());
        return organization;
    }

    public Organization updateOrganizationDto2Entity(OrganizationRequestDTO dto, Long id){
        Organization organization = organizationDto2Entity(dto);
        organization.setId(id);
        return organization;
    }

    public OrganizationResponseDTO organizationEntity2Dto(Organization organization){
        OrganizationResponseDTO dto = new OrganizationResponseDTO();
        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setImage(organization.getImage());
        dto.setAddress(organization.getAddress());
        dto.setPhone(organization.getPhone());
        dto.setEmail(organization.getEmail());
        dto.setWelcomeText(organization.getWelcomeText());
        dto.setAboutUs(organization.getAboutUs());
        dto.setCreationDate(organization.getCreationDate());
        dto.setUpdateDate(organization.getUpdateDate());
        dto.setDeleted(organization.getDeleted());
        return dto;
    }

    public Organization orgPublicDTO2orgEntity (OrganizationResponseDTO dto) {
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setImage(dto.getImage());
        organization.setPhone(dto.getPhone());
        organization.setAddress(dto.getAddress());
        return organization;
    }

    public OrganizationResponseDTO orgEntity2orgPublicDTO (Optional<Organization> organization) {
        OrganizationResponseDTO publicdto = new OrganizationResponseDTO();
        Organization org = organization.get();
        publicdto.setName(org.getName());
        publicdto.setPhone(org.getPhone());
        publicdto.setAddress(org.getAddress());
        publicdto.setImage(org.getImage());
        return publicdto;
    }
}
