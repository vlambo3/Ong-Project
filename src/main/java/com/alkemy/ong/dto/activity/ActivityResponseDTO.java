package com.alkemy.ong.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {

    private Long id;
    private String name;
    private String content;
    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}
