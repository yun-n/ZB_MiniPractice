package com.example.demo.book.entity;

import com.example.demo.book.dto.BookRequestDto;
import com.example.demo.category.entity.Category;
import com.example.demo.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String bookWriter;

    private String year;

    private String ISBN;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getBooks().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getBooks().remove(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getBooks().add(this);
    }

    public void update(Book book){
        this.bookName = book.getBookName();
        this.bookWriter = book.getBookWriter();
        this.year = book.getYear();
        this.ISBN = book.getISBN();
    }

    public Book(Book entity){
        this.id = entity.getId();
        this.bookName = entity.getBookName();
        this.bookWriter = entity.getBookWriter();
        this.year = entity.getYear();
        this.ISBN = entity.getISBN();
    }

    @Builder
    public Book(BookRequestDto bookRequestDto){
        this.bookName = bookRequestDto.getBookName();
        this.bookWriter = bookRequestDto.getBookWriter();
        this.year = bookRequestDto.getYear();
        this.ISBN = bookRequestDto.getISBN();
    }


}
