package org.example.publishhousebooks.service;

import org.example.publishhousebooks.model.dtos.AddBookDTO;
import org.example.publishhousebooks.model.dtos.AddCategoryDTO;

public interface BookService {
    void addBook(AddBookDTO addBookDTO);
}
