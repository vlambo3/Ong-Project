package com.alkemy.ong.endpoints.util.member;


import com.alkemy.ong.dto.member.MemberBasicResponseDto;
import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MemberTestUtils {

    public MemberRequestDto generateRequestDto() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("Member name");
        dto.setImage("MemberImage.jpeg");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberResponseDto generateResponseDTO() {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(1L);
        dto.setName("Member name");
        dto.setImage("MemberImage.jpeg");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberBasicResponseDto generateBasicResponseDTO() {
        MemberBasicResponseDto dto = new MemberBasicResponseDto();
        dto.setName("Member name");
        dto.setImage("MemberImage.jpeg");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberRequestDto generateRequestDtoWithNullName() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName(null);
        dto.setImage("MemberImage.jpeg");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberRequestDto generateRequestDtoWithBlankName() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("");
        dto.setImage("MemberImage.jpeg");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberRequestDto generateRequestDtoWithNullImage() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("Member name");
        dto.setImage(null);
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberRequestDto generateRequestDtoWithBlankImage() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("Member name");
        dto.setImage("");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("Member description");
        return dto;
    }

    public MemberRequestDto generateRequestDtoWithNullDescriptionText() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("Member name");
        dto.setImage("MemberImage.jpeg");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription(null);
        return dto;
    }

    public MemberRequestDto generateRequestDtoWithBlankDescriptionText() {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("Member name");
        dto.setImage("MemberImage.jpeg");
        dto.setFacebookUrl("Member Facebook URL");
        dto.setInstagramUrl("Member Instagram URL");
        dto.setLinkedinUrl("Member LinkedIn URL");
        dto.setDescription("");
        return dto;
    }

}
