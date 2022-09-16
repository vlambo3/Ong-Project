package com.alkemy.ong.dto.category;

import java.time.LocalDateTime;
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
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private String image;

}
