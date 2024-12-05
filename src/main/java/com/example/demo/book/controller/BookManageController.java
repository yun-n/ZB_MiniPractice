package com.example.demo.book.controller;

import com.example.demo.book.dto.BookRequestDto;
import com.example.demo.book.dto.BookResponseDto;
import com.example.demo.book.entity.Book;
import com.example.demo.book.service.BookManageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookManageController {

    private final BookManageService bookManageService;

    public BookManageController(BookManageService bookManageService) {
        this.bookManageService = bookManageService;
    }

//    @PostMapping("/api/v1/books")
//    public Book saveBook(@RequestBody Book book){
//        return bookManageService.saveBook(book);
//    }

    @PostMapping("/api/v1/books")
    public Book saveBookWithTagsAndCategory(@RequestBody BookRequestDto bookRequestDto){
        return bookManageService.saveBookWithTagsAndCategory(bookRequestDto);
    }

    @GetMapping("/api/v1/books")
    public List<BookResponseDto> getBookList(){
        return bookManageService.getBookList();
    }

    @GetMapping("/api/v1/books/{id}")
    public BookResponseDto getBook(@Parameter(name = "id", description = "id", example = "1",in = ParameterIn.PATH) @PathVariable("id") Long id){
        return bookManageService.getBook(id);
    }

    @PutMapping("/api/v1/books/{id}")
    public Book updateBook(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id, @RequestBody Book updatedBook){
        return bookManageService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public boolean deleteBook(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id){
        return bookManageService.deleteBook(id);
    }

}
