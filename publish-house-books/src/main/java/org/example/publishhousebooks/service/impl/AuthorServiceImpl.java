package org.example.publishhousebooks.service.impl;

import org.example.publishhousebooks.model.dtos.AddAuthorDTO;
import org.example.publishhousebooks.model.dtos.AuthorDetailsDTO;
import org.example.publishhousebooks.model.entities.AuthorEntity;
import org.example.publishhousebooks.repository.AuthorRepository;
import org.example.publishhousebooks.service.AuthorService;
import org.example.publishhousebooks.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        this.authorRepository.save(authorToAdd);
    }

    @Override
    public List<AuthorDetailsDTO> getAllAuthors() {
        return this.authorRepository.findAll().stream().map(AuthorServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public AuthorDetailsDTO getAuthorDetailsDTOByName(String authorName) {
        return this.authorRepository.findByName(authorName).map(AuthorServiceImpl::map).orElseThrow(ObjectNotFoundException::new);
    }

    private static AuthorDetailsDTO map(AuthorEntity author){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(author, AuthorDetailsDTO.class);
    }
}
