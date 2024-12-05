package com.example.demo.tag.controller;

import com.example.demo.tag.dto.TagResponseDto;
import com.example.demo.tag.entity.Tag;
import com.example.demo.tag.service.TagService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/api/v1/tags")
    public Tag saveTag(@RequestBody Tag tag){
        return tagService.saveTag(tag);
    }

    @DeleteMapping("/api/v1/tags/{id}")
    public TagResponseDto deleteTag(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id){
        return tagService.deleteTag(id);
    }
}
