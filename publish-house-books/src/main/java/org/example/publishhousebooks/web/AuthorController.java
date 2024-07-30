package org.example.publishhousebooks.web;

import org.example.publishhousebooks.model.dtos.*;
import org.example.publishhousebooks.service.AuthorService;
import org.example.publishhousebooks.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping("/add-author")
    public ResponseEntity<AddAuthorDTO> addAuthor(@RequestBody AddAuthorDTO addAuthorDTO){
        authorService.addAuthor(addAuthorDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDetailsDTO>> getAllAuthors(){
        return ResponseEntity.ok(this.authorService.getAllAuthors());
    }
    @GetMapping("/authors/{name}")
    public ResponseEntity<AuthorDetailsDTO> getByName(@PathVariable("name") String authorName) {
        return ResponseEntity
                .ok(this.authorService.getAuthorDetailsDTOByName(authorName));
    }
}
