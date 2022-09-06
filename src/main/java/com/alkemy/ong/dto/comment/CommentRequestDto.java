package com.alkemy.ong.dto.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "Body can't be null or empty")
    private String body;
    @NotNull(message = "User can't be null.")
    private Long user_id;
    @NotNull(message = "News can't be null or empty")
    private Long news_id;

}
