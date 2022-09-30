package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.service.IActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final IActivityService service;

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> createNewActivity(@Valid @RequestBody ActivityRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<PageDto<ActivityResponseDTO>> getPage(@RequestParam int page) {
        PageDto<ActivityResponseDTO> pageDto = service.getPage(page);
        return ResponseEntity.ok(pageDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }
}
