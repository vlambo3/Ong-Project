package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact contactDto2ContactEntity(ContactDTO dto) {
        Contact entity = new Contact();
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        return entity;
    }

    public ContactDTO contactEntity2ContactDto(Contact entity) {
        ContactDTO dto = new ContactDTO();
        dto.setId(entity.getId());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setMessage(entity.getMessage());
        return dto;
    }
}
