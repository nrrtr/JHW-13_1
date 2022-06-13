package ru.netology.products.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String string) {
        super(string);
    }
}
