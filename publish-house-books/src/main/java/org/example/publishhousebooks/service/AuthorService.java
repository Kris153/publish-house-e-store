package org.example.publishhousebooks.service;

import org.example.publishhousebooks.model.dtos.AddAuthorDTO;
import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.model.dtos.AuthorDetailsDTO;

import java.util.List;

public interface AuthorService {
    void addAuthor(AddAuthorDTO addAuthorDTO);
    List<AuthorDetailsDTO> getAllAuthors();
}
