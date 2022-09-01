package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.UnableToSaveEntityException;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactMapper mapper;
    private final ContactRepository repository;
    private final MessageSource messageSource;

    public ContactDto save(ContactDto dto) {
        try {
            Contact entity = mapper.contactDto2ContactEntity(dto);
            Contact savedEntity = repository.save(entity);
            return mapper.contactEntity2ContactDto(savedEntity);
        } catch (Exception e) {
            throw new UnableToSaveEntityException(messageSource.getMessage("unable-to-save-entity", null, Locale.US));
        }
    }

}
