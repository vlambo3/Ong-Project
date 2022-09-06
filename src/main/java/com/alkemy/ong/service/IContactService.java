package com.alkemy.ong.service;

import com.alkemy.ong.dto.contact.ContactRequestDto;
import com.alkemy.ong.dto.contact.ContactResponseDto;

public interface IContactService {

    ContactResponseDto save(ContactRequestDto dto);
}
