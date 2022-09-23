package com.alkemy.ong.dto.category;

import java.time.LocalDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    @ApiModelProperty(position = 0)
    private Long id;
    @ApiModelProperty(position = 1)
    private String name;
    @ApiModelProperty(position = 2)
    private String description;
    @ApiModelProperty(position = 4)
    private LocalDateTime creationDate;
    @ApiModelProperty(position = 5)
    private LocalDateTime updateDate;
    @ApiModelProperty(position = 3)
    private String image;

}
