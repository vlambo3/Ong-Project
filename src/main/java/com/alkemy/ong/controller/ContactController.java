package com.alkemy.ong.controller;

import com.alkemy.ong.dto.contact.ContactRequestDto;
import com.alkemy.ong.dto.contact.ContactResponseDto;
import com.alkemy.ong.service.IContactService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService service;

    @PostMapping
    public ResponseEntity<ContactResponseDto> save(@Valid @RequestBody ContactRequestDto contact) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(contact));
    }

}
