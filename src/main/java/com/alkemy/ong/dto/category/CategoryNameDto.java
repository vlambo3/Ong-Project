package com.alkemy.ong.dto.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNameDto {

    @ApiModelProperty(position = 0)
    private String name;
}
