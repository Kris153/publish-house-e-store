package org.example.publishhousebooks.service.impl;

import org.example.publishhousebooks.model.dtos.AddBookDTO;
import org.example.publishhousebooks.model.dtos.BookDetailsDTO;
import org.example.publishhousebooks.model.dtos.CategoryDetailsDTO;
import org.example.publishhousebooks.model.entities.AuthorEntity;
import org.example.publishhousebooks.model.entities.BookEntity;
import org.example.publishhousebooks.model.entities.CategoryEntity;
import org.example.publishhousebooks.repository.AuthorRepository;
import org.example.publishhousebooks.repository.BookRepository;
import org.example.publishhousebooks.repository.CategoryRepository;
import org.example.publishhousebooks.service.BookService;
import org.example.publishhousebooks.service.exeption.BadRequestException;
import org.example.publishhousebooks.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<BookDetailsDTO> getAllBooks() {
        return this.bookRepository.findAll().stream().map(BookServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public BookDetailsDTO getBookDetailsDTOById(Integer id) {
        return this.bookRepository.findById(id).map(BookServiceImpl::map).orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public List<BookDetailsDTO> getAllBooksByCategoryName(String categoryName) {
        Optional<CategoryEntity> categoryOpt = this.categoryRepository.findByName(categoryName);
        if(categoryOpt.isEmpty()){
            throw new BadRequestException();
        }
        return this.bookRepository.findAllByCategory(categoryOpt.get()).stream().map(BookServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer bookId) {
        this.bookRepository.deleteById(bookId);
    }

    private static BookDetailsDTO map(BookEntity book){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(book, BookDetailsDTO.class);
    }
}
