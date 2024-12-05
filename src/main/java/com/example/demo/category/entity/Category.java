package com.example.demo.category.entity;

import com.example.demo.book.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "Category", uniqueConstraints = {
        @UniqueConstraint(name = "code_unique", columnNames = {"code"})
})
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Category(String categoryName, String categoryCode) {
        this.name = categoryName;
        this.code = categoryCode;
    }
}
