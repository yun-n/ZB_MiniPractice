package com.example.demo.book.entity;

import com.example.demo.book.dto.BookRequestDto;
import com.example.demo.category.entity.Category;
import com.example.demo.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    private int rentalCount;

    private int bookQuantity;

    @Version
    private Integer version;  // Optimistic Lock 관리를 위한 버전 필드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public void rentBook() {
        if (bookQuantity <= 0) {
            throw new IllegalStateException("대여 가능 수량이 부족합니다.");
        }
        this.bookQuantity--;
    }

    public void returnBook() {
        this.bookQuantity++;
    }

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

    public void update(BookRequestDto book){
        this.bookName = book.getBookName();
        this.bookWriter = book.getBookWriter();
        this.year = book.getYear();
        this.ISBN = book.getISBN();
        this.bookQuantity = book.getBookQuantity();
    }

    public Book(Book entity){
        this.id = entity.getId();
        this.bookName = entity.getBookName();
        this.bookWriter = entity.getBookWriter();
        this.year = entity.getYear();
        this.ISBN = entity.getISBN();
        this.bookQuantity = entity.getBookQuantity();
    }

    @Builder
    public Book(BookRequestDto bookRequestDto){
        this.bookName = bookRequestDto.getBookName();
        this.bookWriter = bookRequestDto.getBookWriter();
        this.year = bookRequestDto.getYear();
        this.ISBN = bookRequestDto.getISBN();
        this.bookQuantity = bookRequestDto.getBookQuantity();
    }


}
