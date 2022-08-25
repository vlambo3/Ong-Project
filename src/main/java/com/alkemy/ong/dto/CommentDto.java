package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    private Long id;

    @NotNull(message = "Body can't be null.")
    private String body;

    private Long user_id;

    private Long news_id;

}
