package com.alkemy.ong.service;

import com.alkemy.ong.dto.contact.ContactRequestDto;
import com.alkemy.ong.dto.contact.ContactResponseDto;

import java.util.List;

public interface IContactService {


    ContactResponseDto save(ContactRequestDto dto);

    List<ContactResponseDto> findAll();

}
