package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.dto.contact.ContactRequestDto;
import com.alkemy.ong.dto.contact.ContactResponseDto;
import com.alkemy.ong.exception.UnableToSaveEntityException;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.service.IContactService;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.repository.ContactRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final GenericMapper mapper;
    private final ContactRepository repository;
    private final IEmailService emailService;
    private final MessageSource messageSource;

    public ContactResponseDto save(ContactRequestDto dto) {
        try {
            Contact entity = mapper.map(dto, Contact.class);
            Contact savedEntity = repository.save(entity);
            emailService.sendEmail(savedEntity.getEmail());
            return mapper.map(savedEntity, ContactResponseDto.class);
        } catch (Exception e) {
            throw new UnableToSaveEntityException(messageSource.getMessage("unable-to-save-entity", null, Locale.US));
        }
    }

    public List<ContactResponseDto> findAll(){
        List<Contact> contacts = repository.findAll();
        if (contacts.isEmpty()) {
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        }
        return mapper.mapAll(contacts, ContactResponseDto.class);
    }
}
