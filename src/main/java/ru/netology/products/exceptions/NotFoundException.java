package ru.netology.products.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String str) {
        super(str);
    }
}
