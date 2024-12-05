package com.example.demo.category.repository;

import com.example.demo.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);

    @Modifying
    @Query("UPDATE Book b SET b.category = null WHERE b.category.id = :categoryId")
    void removeCategoryFromBooks(@Param("categoryId") Long categoryId);
}
