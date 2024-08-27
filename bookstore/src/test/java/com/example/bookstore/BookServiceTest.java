package com.example.bookstore;

import com.example.bookstore.entity.Book;
import com.example.bookstore.helper.exception.ItemNotFoundException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Title1", "Author1", 10.0));
        books.add(new Book(2L, "Title2", "Author2", 15.0));

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookByIdSuccess() {
        Book book = new Book(1L, "Title1", "Author1", 10.0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Title1", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () -> {
            bookService.getBookById(1L);
        });

        assertEquals("Book not found with ID: 1", thrown.getMessage());
        assertEquals("BOOK_NOT_FOUND", thrown.getErrorCode());

    }

    @Test
    void testCreateBook() {
        Book book = new Book(1L, "Title1", "Author1", 10.0);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.createBook(book);

        //assertNotNull(result);
        assertEquals("Title1", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBookSuccess() {
        Book existingBook = new Book(1L, "Title1", "Author1", 10.0);
        Book updatedBook = new Book(1L, "UpdatedTitle", "UpdatedAuthor", 20.0);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals("UpdatedTitle", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void testUpdateBookNotFound() {
        Book updatedBook = new Book(1L, "UpdatedTitle", "UpdatedAuthor", 20.0);

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () -> {
            bookService.updateBook(1L, updatedBook);
        });

        assertEquals("Book not found with ID: 1", thrown.getMessage());
        assertEquals("BOOK_NOT_FOUND", thrown.getErrorCode());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteBookSuccess() {
        Book book = new Book(1L, "Title1", "Author1", 10.0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testDeleteBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () -> {
            bookService.deleteBook(1L);
        });

        assertEquals("Book not found with ID: 1", thrown.getMessage());
        assertEquals("BOOK_NOT_FOUND", thrown.getErrorCode());
        verify(bookRepository, times(1)).findById(1L);
    }
}
