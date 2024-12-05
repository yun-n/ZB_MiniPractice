package com.example.demo.category.controller;

import com.example.demo.category.dto.CategoryResponseDto;
import com.example.demo.category.entity.Category;
import com.example.demo.category.service.CategoryService;
import com.example.demo.tag.dto.TagResponseDto;
import com.example.demo.tag.entity.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/v1/categories")
    public List<Category> getCategoryList(){
        return categoryService.getCategoryList();
    }

    @PostMapping("/api/v1/categories")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/api/v1/categories/{id}")
    public CategoryResponseDto deleteCategory(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }
}
