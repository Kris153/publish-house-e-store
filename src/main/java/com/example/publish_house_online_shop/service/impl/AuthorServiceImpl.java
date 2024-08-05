package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddAuthorDTO;
import com.example.publish_house_online_shop.model.dtos.AuthorDetailsDTO;
import com.example.publish_house_online_shop.model.dtos.BookDetailsDTO;
import com.example.publish_house_online_shop.service.AuthorService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final RestClient bookRestClient;

    public AuthorServiceImpl(RestClient restClient) {
        this.bookRestClient = restClient;
    }

    @Override
    public void addAuthor(AddAuthorDTO addAuthorDTO) {
        this.bookRestClient.post().uri("/add-author").body(addAuthorDTO).retrieve();
    }

    @Override
    public List<AuthorDetailsDTO> getAllAuthors() {
        return this.bookRestClient.get().uri("/authors").accept(MediaType.APPLICATION_JSON).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public AuthorDetailsDTO getAuthorDetailsDTOByName(String authorName) {
        return this.bookRestClient
                .get()
                .uri("/authors/{name}", authorName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(AuthorDetailsDTO.class);
    }

    @Override
    public boolean doesAuthorExists(String authorName) {
        try {
            return getAuthorDetailsDTOByName(authorName) != null;
        }catch (HttpClientErrorException.NotFound e){
            return false;
        }
    }
}
