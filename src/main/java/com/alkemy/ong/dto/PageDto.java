package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PageDto<E> {

    private List<E> content;
    private String previousPage;
    private String nextPage;

}



