package org.example.publishhousebooks.service.impl;

import org.example.publishhousebooks.model.dtos.AddBookDTO;
import org.example.publishhousebooks.model.entities.AuthorEntity;
import org.example.publishhousebooks.model.entities.BookEntity;
import org.example.publishhousebooks.model.entities.CategoryEntity;
import org.example.publishhousebooks.repository.AuthorRepository;
import org.example.publishhousebooks.repository.BookRepository;
import org.example.publishhousebooks.repository.CategoryRepository;
import org.example.publishhousebooks.service.BookService;
import org.example.publishhousebooks.service.exeption.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addBook(AddBookDTO addBookDTO) {
        Optional<AuthorEntity> authorOpt = this.authorRepository.findByName(addBookDTO.getAuthorName());
        if(authorOpt.isEmpty()){
            throw new BadRequestException();
        }
        Optional<CategoryEntity> categoryOpt = this.categoryRepository.findByName(addBookDTO.getCategoryName());
        if(categoryOpt.isEmpty()){
            throw new BadRequestException();
        }
        BookEntity bookToAdd = this.modelMapper.map(addBookDTO, BookEntity.class);
        bookToAdd.setAuthor(authorOpt.get());
        bookToAdd.setCategory(categoryOpt.get());
        this.bookRepository.saveAndFlush(bookToAdd);
    }
}
