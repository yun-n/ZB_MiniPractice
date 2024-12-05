package com.example.demo.category.service;

import com.example.demo.book.entity.Book;
import com.example.demo.category.dto.CategoryResponseDto;
import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public CategoryResponseDto deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리" + id + " 찾을 수 없습니다."));

        categoryRepository.removeCategoryFromBooks(id);

        categoryRepository.deleteById(id);
        return new CategoryResponseDto(true);

    }
}
