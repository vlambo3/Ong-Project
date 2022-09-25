package com.alkemy.ong.endpoints.util.organization;

import com.alkemy.ong.dto.organization.OrganizationBasicResponseDto;
import com.alkemy.ong.dto.organization.OrganizationRequestDto;
import com.alkemy.ong.dto.organization.OrganizationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrganizationTestUtils {

    public OrganizationRequestDto generateRequestDto() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("Organization name");
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("emailTest@gmail.com");
        dto.setWelcomeText("Organization welcomeText");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationResponseDto generateResponseDTO() {
        OrganizationResponseDto dto = new OrganizationResponseDto();
        dto.setId(1L);
        dto.setName("Organization name");
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText("Organization welcomeText");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationBasicResponseDto generateBasicResponseDTO() {
        OrganizationBasicResponseDto dto = new OrganizationBasicResponseDto();
        dto.setName("Organization name");
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        return dto;
    }

    public OrganizationRequestDto generateRequestDtoWithNullName() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName(null);
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText("Organization welcomeText");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationRequestDto generateRequestDtoWithBlankName() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("");
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText("Organization welcomeText");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationRequestDto generateRequestDtoWithNullImage() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("Organization name");
        dto.setImage(null);
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText("Organization welcomeText");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationRequestDto generateRequestDtoWithBlankImage() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("Organization name");
        dto.setImage("");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText("Organization welcomeText");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationRequestDto generateRequestDtoWithNullWelcomeText() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("Organization name");
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText(null);
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }

    public OrganizationRequestDto generateRequestDtoWithBlankWelcomeText() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("");
        dto.setImage("OrganizationImage.jpeg");
        dto.setAddress("Organization address");
        dto.setPhone("Organization phone");
        dto.setEmail("Organization email");
        dto.setWelcomeText("");
        dto.setAboutUs("Organization aboutUs");
        return dto;
    }


}


