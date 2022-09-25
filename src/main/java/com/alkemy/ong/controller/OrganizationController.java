package com.alkemy.ong.controller;

import com.alkemy.ong.dto.organization.OrganizationRequestDto;
import com.alkemy.ong.dto.organization.OrganizationResponseDto;
import com.alkemy.ong.dto.organization.OrganizationBasicResponseDto;
import com.alkemy.ong.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService service;

    @PostMapping("/public")
    public ResponseEntity<OrganizationResponseDto> update(@Valid @RequestBody OrganizationRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(dto));
    }

    @GetMapping("/public")
    public ResponseEntity<OrganizationBasicResponseDto> getPublicInfo() {
        OrganizationBasicResponseDto dto = service.getPublicInfo();
        return ResponseEntity.ok().body(dto);
    }
    
}
