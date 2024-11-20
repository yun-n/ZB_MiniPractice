package com.example.demo.book.controller;

import com.example.demo.book.entity.Book;
import com.example.demo.book.service.BookManageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class bookManageController {

    private final BookManageService bookManageService;

    public bookManageController(BookManageService bookManageService) {
        this.bookManageService = bookManageService;
    }

    @PostMapping("/api/save/book")
    public Book saveBook(@RequestBody Book book){
        return bookManageService.saveBook(book);
    }

    @GetMapping("/api/get/book/list")
    public List<Book> getBookList(){
        return bookManageService.getBookList();
    }

    @GetMapping("/api/get/book/{id}")
    public Book getBook(@Parameter(name = "id", description = "id", example = "1",in = ParameterIn.PATH) @PathVariable("id") Long id){
        return bookManageService.getBook(id);
    }

    @PutMapping("/api/update/book/{id}")
    public Book updateBook(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id, @RequestBody Book updatedBook){
        return bookManageService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/api/delete/book/{id}")
    public boolean deleteBook(@Parameter(name = "id", description = "id", example = "1", in = ParameterIn.PATH) @PathVariable("id") Long id){
        return bookManageService.deleteBook(id);
    }

}
