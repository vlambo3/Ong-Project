package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    private Long id;

    private String body;

    private Long user_id;

    private Long news_id;

}
