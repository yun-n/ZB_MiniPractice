package com.example.demo.book.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.repository.BookManageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManageService {

    private final BookManageRepository bookManageRepository;

    public BookManageService(BookManageRepository bookManageRepository) {
        this.bookManageRepository = bookManageRepository;
    }

    public Book saveBook(Book book) {
        return bookManageRepository.save(book);
    }

    public List<Book> getBookList() {
        return bookManageRepository.findAll();
    }

    public Book getBook(Long id) {
        Book book = bookManageRepository.findById(id);

        if(book == null){
            throw new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다.");
        }

        return book;
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookManageRepository.findById(id);

        if(book == null){
            throw new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다.");
        }

        book.setBookName(updatedBook.getBookName());
        book.setBookWriter(updatedBook.getBookWriter());
        book.setYear(updatedBook.getYear());
        book.setISBN(updatedBook.getISBN());

        return bookManageRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        Book book = bookManageRepository.findById(id);

        if(book == null){
            throw new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다.");
        }

        return bookManageRepository.deleteById(id);
    }
}
