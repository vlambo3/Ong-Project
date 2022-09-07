package com.alkemy.ong.controller;

import com.alkemy.ong.dto.organization.OrganizationRequestDTO;
import com.alkemy.ong.dto.organization.OrganizationResponseDTO;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.service.ISlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService service;

    @Autowired
    private ISlideService slideService;

    @PostMapping("/public")
    public ResponseEntity<OrganizationResponseDTO> update(@Valid @RequestBody OrganizationRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @GetMapping("/public")
    public ResponseEntity<OrganizationResponseDTO> getPublicInfo() {
        OrganizationResponseDTO dtos = service.getPublicInfo();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/public/{organizationId}")
    public ResponseEntity<List<SlideResponseDto>> getOrganizationSlide(
            @PathVariable(name="organizationId", required=true)
            Long organizationId){

        return ResponseEntity.ok(slideService.getSlidesForOrganizationByOrder(organizationId));

    }
}
