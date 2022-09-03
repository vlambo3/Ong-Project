package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDTO;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService service;

    @PostMapping("/public")
    public ResponseEntity<OrganizationDto> update(@Valid @RequestBody OrganizationDto organizationDto) throws Exception {
        return ResponseEntity.ok().body(service.update(organizationDto));
    }
    @GetMapping("/public")
    public ResponseEntity<OrganizationPublicDTO> getPublicInfo() {
        OrganizationPublicDTO dtos = service.getPublicInfo();
        return ResponseEntity.ok().body(dtos);
    }
}
