package com.example.demo.category.service;

import com.example.demo.category.dto.CategoryRequestDto;
import com.example.demo.category.dto.CategoryResponseDto;
import com.example.demo.category.dto.CategoryResultResponseDto;
import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDto> getCategoryList() {
        return categoryRepository.findAll().stream().map(CategoryResponseDto::new).collect(Collectors.toList());
    }

    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category(categoryRequestDto.getName(),categoryRequestDto.getCode());
        Category savedcategory = categoryRepository.save(category);
        return new CategoryResponseDto(savedcategory);
    }

    @Transactional
    public CategoryResultResponseDto deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리" + id + " 찾을 수 없습니다."));

        categoryRepository.removeCategoryFromBooks(id);

        categoryRepository.deleteById(id);
        return new CategoryResultResponseDto(true);

    }
}
