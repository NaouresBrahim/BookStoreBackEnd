package com.example.bookstore.helper.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {
    private final String errorCode;

    public ItemNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}