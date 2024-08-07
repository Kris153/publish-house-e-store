package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.entities.BookEntity;
import com.example.publish_house_online_shop.model.entities.CartEntity;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.repository.BookRepository;
import com.example.publish_house_online_shop.repository.CartRepository;
import com.example.publish_house_online_shop.service.CartService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;

    public CartServiceImpl(BookRepository bookRepository, CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
    }
    @Transactional
    @Override
    public void addToCartById(Integer bookId, UserEntity user) {
        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(user);
        if(cartOpt.isEmpty()){
            throw new BadRequestException();
        }
        Optional<BookEntity> bookOpt = this.bookRepository.findById(bookId);
        if(bookOpt.isEmpty()){
            throw new BadRequestException();
        }
        CartEntity cart = cartOpt.get();
        List<BookEntity> books = cart.getBooks();
        books.add(bookOpt.get());
        cart.setBooks(books);
        cart.updateTotalPrice();
        cart.setLastModified(Instant.now());
        this.cartRepository.saveAndFlush(cart);
    }
    @Transactional
    @Override
    public void removeFromCartById(Integer bookId, UserEntity user) {
        Optional<CartEntity> cartOpt = this.cartRepository.findByUser(user);
        if(cartOpt.isEmpty()){
            throw new BadRequestException();
        }
        Optional<BookEntity> bookOpt = this.bookRepository.findById(bookId);
        if(bookOpt.isEmpty()){
            throw new BadRequestException();
        }
        CartEntity cart = cartOpt.get();
        List<BookEntity> books = cart.getBooks();
        books.remove(bookOpt.get());
        cart.setBooks(books);
        cart.updateTotalPrice();
        cart.setLastModified(Instant.now());
        this.cartRepository.saveAndFlush(cart);
    }
}
