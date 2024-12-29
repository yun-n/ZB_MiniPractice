package com.example.demo.tag.controller;

import com.example.demo.tag.dto.TagRequestDto;
import com.example.demo.tag.dto.TagResponseDto;
import com.example.demo.tag.service.TagService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/api/v1/tags")
    public TagResponseDto saveTag(@Valid @RequestBody TagRequestDto tagRequestDto){
        return tagService.saveTag(tagRequestDto);
    }

    @DeleteMapping("/api/v1/tags/{id}")
    public ResponseEntity<Void> deleteTag(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id){
        tagService.deleteTag(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
