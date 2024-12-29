package com.example.demo.book.controller;

import com.example.demo.book.dto.BookRequestDto;
import com.example.demo.book.dto.BookResponseDto;
import com.example.demo.book.entity.Book;
import com.example.demo.book.service.BookManageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public BookResponseDto saveBookWithTagsAndCategory(@Valid @RequestBody BookRequestDto bookRequestDto){
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
    public BookResponseDto updateBook(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id,
                                      @RequestBody @Valid BookRequestDto updatedBook){
        return bookManageService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public ResponseEntity<Void> deleteBook(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id){
        bookManageService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
