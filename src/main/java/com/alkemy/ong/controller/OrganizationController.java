package com.alkemy.ong.controller;

import com.alkemy.ong.dto.organization.OrganizationRequestDTO;
import com.alkemy.ong.dto.organization.OrganizationResponseDTO;
import com.alkemy.ong.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService service;

    @PostMapping("/public")
    public ResponseEntity<OrganizationResponseDTO> update(@Valid @RequestBody OrganizationRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @GetMapping("/public")
    public ResponseEntity<OrganizationResponseDTO> getPublicInfo() {
        OrganizationResponseDTO dtos = service.getPublicInfo();
        return ResponseEntity.ok().body(dtos);
    }
}
