package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

public interface CommentService {

    //TODO to review as required
    CommentDto save(CommentDto commentDto);

    //TODO to review as required
    void delete(Long id);

    //TODO to review as required
    CommentDto put(Long id, CommentDto edit);


}
