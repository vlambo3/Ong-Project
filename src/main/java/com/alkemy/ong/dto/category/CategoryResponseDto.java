package com.alkemy.ong.dto.category;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    
    private Long id;
    private String name;
    private String description;
    private Timestamp creationTimestamp;
    private Timestamp updateTimeStamp;
    private String image;
    private Boolean deleted;

}
