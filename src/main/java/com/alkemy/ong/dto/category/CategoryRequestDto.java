package com.alkemy.ong.dto.category;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Model Category - Request")
public class CategoryRequestDto implements Serializable {

    @ApiModelProperty(value = "Category name", dataType = "String", required = true, example = "Name Category")
    @NotBlank(message = "Name can't be null or empty")
    private String name;

    @ApiModelProperty(value = "Category description", dataType = "String", example = "Description of Category")
    private String description;

    @ApiModelProperty(value = "Category image", dataType = "String", example = "Image-url")
    private String image;

}

