package com.alkemy.ong.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDTO {

    private String name;
    private String content;
    private String image;

}
