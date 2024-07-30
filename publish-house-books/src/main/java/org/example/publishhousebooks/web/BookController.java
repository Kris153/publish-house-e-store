package org.example.publishhousebooks.web;

import org.example.publishhousebooks.model.dtos.AddBookDTO;
import org.example.publishhousebooks.model.dtos.BookDetailsDTO;
import org.example.publishhousebooks.model.dtos.CategoryDetailsDTO;
import org.example.publishhousebooks.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/books")
    public ResponseEntity<List<BookDetailsDTO>> getAllBooks(){
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDetailsDTO> getById(@PathVariable("id") Integer bookId) {
        return ResponseEntity
                .ok(this.bookService.getBookDetailsDTOById(bookId));
    }
    @GetMapping("/books/categories/{name}")
    public ResponseEntity<List<BookDetailsDTO>> getByCategory(@PathVariable("name") String categoryName) {
        return ResponseEntity
                .ok(this.bookService.getAllBooksByCategoryName(categoryName));
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookDetailsDTO> deleteById(@PathVariable("id") Integer bookId){
        this.bookService.deleteById(bookId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
