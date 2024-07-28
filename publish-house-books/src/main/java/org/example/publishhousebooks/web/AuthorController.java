package org.example.publishhousebooks.web;

import org.example.publishhousebooks.model.dtos.AddAuthorDTO;
import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.service.AuthorService;
import org.example.publishhousebooks.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
