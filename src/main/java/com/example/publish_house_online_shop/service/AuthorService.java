package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.AddAuthorDTO;
import com.example.publish_house_online_shop.model.dtos.AuthorDetailsDTO;

import java.util.List;

public interface AuthorService {
    void addAuthor(AddAuthorDTO addAuthorDTO);
    List<AuthorDetailsDTO> getAllAuthors();
}
