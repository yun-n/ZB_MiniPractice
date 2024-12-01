package com.example.demo.book.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.repository.JpaBookManageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookManageService {

    private final JpaBookManageRepository jpaBookManageRepository;

    public BookManageService(JpaBookManageRepository jpaBookManageRepository) {
        this.jpaBookManageRepository = jpaBookManageRepository;
    }

    public Book saveBook(Book book) {
        return jpaBookManageRepository.save(book);
    }

    public List<Book> getBookList() {
        return jpaBookManageRepository.findAll();
    }

    public Book getBook(Long id) {
        return jpaBookManageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다."));
    }

    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        Book book = jpaBookManageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다."));

        book.update(updatedBook);

        return new Book(book);
    }

    public boolean deleteBook(Long id) {
        jpaBookManageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다."));

        jpaBookManageRepository.deleteById(id);

        return true;
    }
}
