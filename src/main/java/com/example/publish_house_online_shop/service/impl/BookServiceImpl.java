package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddBookDTO;
import com.example.publish_house_online_shop.model.dtos.AddCategoryDTO;
import com.example.publish_house_online_shop.model.dtos.BookDetailsDTO;
import com.example.publish_house_online_shop.model.entities.BookEntity;
import com.example.publish_house_online_shop.repository.BookRepository;
import com.example.publish_house_online_shop.service.BookService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final RestClient bookRestClient;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(RestClient bookRestClient, BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRestClient = bookRestClient;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addBook(AddBookDTO addBookDTO) {
        this.bookRestClient.post().uri("/add-book").body(addBookDTO).retrieve();
        BookEntity bookToAdd = this.modelMapper.map(addBookDTO, BookEntity.class);
        bookToAdd.setDeleted(false);
        this.bookRepository.saveAndFlush(bookToAdd);
    }

    @Override
    public List<BookDetailsDTO> getAllBooks() {
        return this.bookRestClient.get().uri("/books").accept(MediaType.APPLICATION_JSON).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public BookDetailsDTO getBookDetailsDTOById(Integer bookId) {
        return this.bookRestClient
                .get()
                .uri("/books/{id}", bookId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(BookDetailsDTO.class);
    }

    @Override
    public List<BookDetailsDTO> getAllBooksByCategory(String categoryName) {
        return this.bookRestClient
                .get()
                .uri("/books/categories/{name}", categoryName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Override
    public void deleteById(Integer bookId) {
        this.bookRestClient
                .delete()
                .uri("/books/{id}", bookId)
                .retrieve();
        Optional<BookEntity> bookOpt = this.bookRepository.findById(bookId);
        if(bookOpt.isEmpty()){
            throw new BadRequestException();
        }
        BookEntity bookEntity = bookOpt.get();
        bookEntity.setDeleted(true);
        this.bookRepository.saveAndFlush(bookEntity);
    }
}
