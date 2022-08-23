package com.alkemy.ong.security.service;

import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;

public interface IUserService {

    UserResponseDto save(UserRequestDto dto);

}
