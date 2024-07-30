package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.AddBookDTO;
import com.example.publish_house_online_shop.model.dtos.BookDetailsDTO;

import java.util.List;

public interface BookService {
    void addBook(AddBookDTO addBookDTO);

    List<BookDetailsDTO> getAllBooks();

    BookDetailsDTO getBookDetailsDTOById(Integer bookId);

    List<BookDetailsDTO> getAllBooksByCategory(String categoryName);

    void deleteById(Integer bookId);
}
