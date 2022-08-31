package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactMapper mapper;
    private final ContactRepository repository;

    public ContactDto save(ContactDto dto) {
        Contact entity = mapper.contactDto2ContactEntity(dto);
        Contact savedEntity = repository.save(entity);
        return mapper.contactEntity2ContactDto(savedEntity);
    }

}
