package com.example.demo.book.repository;

import com.example.demo.book.entity.Book;

import java.util.List;

public interface BookManageRepository {

    Book save(Book book);

    List<Book> findAll();

    Book findById(Long id);

    boolean deleteById(Long id);
}
