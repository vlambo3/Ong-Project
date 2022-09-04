package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationPublicDTO;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/public")
    public ResponseEntity<OrganizationPublicDTO> getPublicInfo() {
        OrganizationPublicDTO dtos = organizationService.getPublicInfo();
        return ResponseEntity.ok().body(dtos);
    }
}
