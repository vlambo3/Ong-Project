package com.alkemy.ong.dto.member;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    @ApiModelProperty(notes = "Member Name",example = "Full name Member",required = true,position = 0)
    @NotBlank(message = "Image may not be null or empty")
    private String name;
    @ApiModelProperty(notes = "Member Facebook URL",example = "https://www.facebook.com/username",required = false,position = 1)
    private String facebookUrl;
    @ApiModelProperty(notes = "Member Instagram URL",example = "https://www.instagram.com/username/",required = false,position = 2)
    private String instagramUrl;
    @ApiModelProperty(notes = "Member Linkedin URL",example = "https://www.linkedin.com/in/username/",required = false,position = 3)
    private String linkedinUrl;
    @ApiModelProperty(notes = "Member Image URL",example = "",required = true,position = 4)
    @NotBlank(message = "Image may not be null or empty")
    private String image;
    @ApiModelProperty(notes = "Member Description",example = "brief description of the member",required = false,position = 5)
    private String description;
}
