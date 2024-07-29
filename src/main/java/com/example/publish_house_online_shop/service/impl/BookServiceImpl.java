package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddBookDTO;
import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
import com.example.publish_house_online_shop.model.dtos.BookDetailsDTO;
import com.example.publish_house_online_shop.service.BookService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final RestClient bookRestClient;

    public BookServiceImpl(RestClient bookRestClient) {
        this.bookRestClient = bookRestClient;
    }

    @Override
    public void addBook(AddBookDTO addBookDTO) {
        this.bookRestClient.post().uri("/add-book").body(addBookDTO).retrieve();
    }

    @Override
    public List<BookDetailsDTO> getAllBooks() {
        return this.bookRestClient.get().uri("/books").accept(MediaType.APPLICATION_JSON).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }
}
