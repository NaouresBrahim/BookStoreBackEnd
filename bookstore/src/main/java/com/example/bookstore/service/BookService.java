package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookStoreRepository;

    public BookService(BookRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        log.info("here the list of available books{}", books);
        return new ArrayList<>(bookStoreRepository.findAll());
    }
}
