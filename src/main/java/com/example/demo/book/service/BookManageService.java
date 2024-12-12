package com.example.demo.book.service;

import com.example.demo.book.dto.BookRequestDto;
import com.example.demo.book.dto.BookResponseDto;
import com.example.demo.book.entity.Book;
import com.example.demo.book.repository.JpaBookManageRepository;
import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import com.example.demo.tag.entity.Tag;
import com.example.demo.tag.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookManageService {

    private final JpaBookManageRepository jpaBookManageRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    public BookManageService(JpaBookManageRepository jpaBookManageRepository, TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.jpaBookManageRepository = jpaBookManageRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }

//    public Book saveBook(Book book) {
//        return jpaBookManageRepository.save(book);
//    }

    @Transactional
    public BookResponseDto saveBookWithTagsAndCategory(BookRequestDto bookRequestDto){
        Book book = new Book(bookRequestDto);

        for(String tagName : bookRequestDto.getTagNames()){
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(tagName)));
            book.addTag(tag);
        }
        if (!bookRequestDto.getCategoryName().isEmpty() && !bookRequestDto.getCategoryCode().isEmpty()) {
            Category category = categoryRepository.findByName(bookRequestDto.getCategoryName())
                    .orElseGet(() -> categoryRepository.save(new Category(bookRequestDto.getCategoryName(),bookRequestDto.getCategoryCode())));
            book.setCategory(category);
        }

        Book savedBook = jpaBookManageRepository.save(book);
        return new BookResponseDto(savedBook);
    }

    public List<BookResponseDto> getBookList() {
        return jpaBookManageRepository.findAll().stream().map(BookResponseDto::new).collect(Collectors.toList());
    }

    public BookResponseDto getBook(Long id) {
        return jpaBookManageRepository.findById(id).map(BookResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다."));
    }

    @Transactional
    public BookResponseDto updateBook(Long id, BookRequestDto updatedBook) {
        Book book = jpaBookManageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다."));

        book.update(updatedBook);

        return new BookResponseDto(book);
    }

    public boolean deleteBook(Long id) {
        Book book = jpaBookManageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서" + id + " 를 찾을 수 없습니다."));

        book.getTags().forEach(tag -> tag.getBooks().remove(book));
        book.getCategory().getBooks().remove(book);

        jpaBookManageRepository.deleteById(id);
        return true;
    }

}
