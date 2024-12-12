package com.example.demo.tag.dto;

import com.example.demo.tag.entity.Tag;
import lombok.Getter;

@Getter
public class TagResponseDto {

    private Long id;
    private String name;

    public TagResponseDto(Tag tag){
        this.id = tag.getId();
        this.name = tag.getName();
    }
}
