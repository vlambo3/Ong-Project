package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

public interface CommentService {

    CommentDto save(CommentDto commentDto);

    void delete(Long id);

    CommentDto put(Long id, CommentDto edit);


}
