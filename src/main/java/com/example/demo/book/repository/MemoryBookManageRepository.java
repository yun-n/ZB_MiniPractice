package com.example.demo.book.repository;

import com.example.demo.book.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryBookManageRepository implements BookManageRepository{

    static Map<Long, Book> bookStore = new HashMap<>();
    long sequence = 0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        bookStore.put(book.getId(),book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }

    @Override
    public Book findById(Long id) {
        return bookStore.get(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return bookStore.remove(id) != null;
    }

}
