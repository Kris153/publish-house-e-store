package org.example.publishhousebooks.service.impl;

import org.example.publishhousebooks.model.dtos.AddAuthorDTO;
import org.example.publishhousebooks.model.entities.AuthorEntity;
import org.example.publishhousebooks.repository.AuthorRepository;
import org.example.publishhousebooks.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAuthor(AddAuthorDTO addAuthorDTO) {
        AuthorEntity authorToAdd = this.modelMapper.map(addAuthorDTO, AuthorEntity.class);
        this.authorRepository.saveAndFlush(authorToAdd);
    }
}
