package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    private Long id;

    @NotNull(message = "Body can't be null.")
    private String body;

    private Long user_id;

    private Long news_id;

}
