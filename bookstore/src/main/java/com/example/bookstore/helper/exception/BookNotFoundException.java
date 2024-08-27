package com.example.bookstore.helper.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends RuntimeException {
    private final String errorCode;

    public BookNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}