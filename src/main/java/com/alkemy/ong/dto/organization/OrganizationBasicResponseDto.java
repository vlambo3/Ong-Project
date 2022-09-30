package com.alkemy.ong.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationBasicResponseDto {
    
    private String name;
    private String image;
    private String phone;
    private String address;

}
