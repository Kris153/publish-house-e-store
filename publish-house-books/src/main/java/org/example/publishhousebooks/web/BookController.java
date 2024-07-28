package org.example.publishhousebooks.web;

import org.example.publishhousebooks.model.dtos.AddBookDTO;
import org.example.publishhousebooks.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add-book")
    public ResponseEntity<AddBookDTO> addBook(@RequestBody AddBookDTO addBookDTO){
        bookService.addBook(addBookDTO);
        return ResponseEntity.ok().build();
    }
}
