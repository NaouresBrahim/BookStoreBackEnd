package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.helper.exception.ItemNotFoundException;
import com.example.bookstore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Book not found with ID: " + id, "BOOK_NOT_FOUND"));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Book not found with ID: " + id, "BOOK_NOT_FOUND"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Book not found with ID: " + id, "BOOK_NOT_FOUND"));
        bookRepository.delete(book);
    }
}
