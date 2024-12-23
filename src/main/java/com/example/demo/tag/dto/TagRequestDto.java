package com.example.demo.tag.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequestDto {
    @NotEmpty
    private String name;
}
