package ru.bogdanov.diplom.data.exception;

public class UnexpectedException extends RuntimeException {

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Exception ex) {
        super(message, ex);
    }

}
