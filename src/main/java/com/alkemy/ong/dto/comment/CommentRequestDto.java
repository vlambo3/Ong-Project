package com.alkemy.ong.dto.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "Body can't be null or empty")
    private String body;
    @NotNull(message = "User can't be null.")
    private Long userId;
    @NotNull(message = "News can't be null or empty")
    private Long newsId;

}
