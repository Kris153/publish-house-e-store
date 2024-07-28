package org.example.publishhousebooks.service;

import org.example.publishhousebooks.model.dtos.AddAuthorDTO;
import org.example.publishhousebooks.model.dtos.AddCategoryDTO;

public interface AuthorService {
    void addAuthor(AddAuthorDTO addAuthorDTO);
}
