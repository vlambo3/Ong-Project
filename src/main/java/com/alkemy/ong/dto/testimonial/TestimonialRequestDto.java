package com.alkemy.ong.dto.testimonial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Model Testimonial - Request")
public class TestimonialRequestDto implements Serializable {

    @ApiModelProperty(value = "Testimonial name",dataType = "String", required = true, example = "Name testimony")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "Testimonial image", dataType = "String", example = "Image-url")
    private String image;

    @ApiModelProperty(value = "Testimonial content", dataType = "String", required = true, example = "Content of testimonial")
    @NotBlank(message = "Content cannot be empty")
    private String content;
}
