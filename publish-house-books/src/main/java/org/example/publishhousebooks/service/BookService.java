package org.example.publishhousebooks.service;

import org.example.publishhousebooks.model.dtos.AddBookDTO;
import org.example.publishhousebooks.model.dtos.AddCategoryDTO;
import org.example.publishhousebooks.model.dtos.BookDetailsDTO;

import java.util.List;

public interface BookService {
    void addBook(AddBookDTO addBookDTO);

    List<BookDetailsDTO> getAllBooks();

    BookDetailsDTO getBookDetailsDTOById(Integer id);

    List<BookDetailsDTO> getAllBooksByCategoryName(String categoryName);

    void deleteById(Integer bookId);
}
