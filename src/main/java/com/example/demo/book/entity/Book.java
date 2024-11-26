package com.example.demo.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String bookWriter;

    private String year;

    private String ISBN;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
