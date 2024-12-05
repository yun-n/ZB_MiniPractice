package com.example.demo.tag.entity;

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
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }
}
