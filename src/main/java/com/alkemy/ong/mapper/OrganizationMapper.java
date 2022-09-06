package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDTO;
import com.alkemy.ong.model.Organization;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrganizationMapper {

    public Organization organizationDto2Entity(OrganizationDto dto){
        Organization organization = new Organization();
        organization.setEmail(dto.getEmail());
        organization.setPhone(dto.getPhone());
        organization.setAboutUs(dto.getAboutUs());
        organization.setImg(dto.getImg());
        organization.setWelcomeText(dto.getWelcomeText());
        organization.setName(dto.getName());
        organization.setAddress(dto.getAddress());
        return organization;
    }

    public OrganizationDto organizationEntity2Dto(Organization organization){
        OrganizationDto dto = new OrganizationDto();
        dto.setAddress(organization.getAddress());
        dto.setImg(organization.getImg());
        dto.setName(organization.getName());
        dto.setWelcomeText(organization.getWelcomeText());
        dto.setAboutUs(organization.getAboutUs());
        dto.setPhone(organization.getPhone());
        dto.setEmail(organization.getEmail());
        return dto;
    }
    public Organization orgPublicDTO2orgEntity (OrganizationPublicDTO dto) {
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setImg(dto.getImage());
        organization.setPhone(dto.getPhone());
        organization.setAddress(dto.getAddress());
        return organization;
    }

    public OrganizationPublicDTO orgEntity2orgPublicDTO (Optional<Organization> organization) {
        OrganizationPublicDTO publicdto = new OrganizationPublicDTO();
        Organization org = organization.get();
        publicdto.setName(org.getName());
        publicdto.setPhone(org.getPhone());
        publicdto.setAddress(org.getAddress());
        publicdto.setImage(org.getImg());
        return publicdto;
    }
}
