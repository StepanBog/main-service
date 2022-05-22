package ru.bogdanov.diplom.data.exception;

import lombok.Getter;
import ru.bogdanov.diplom.data.enums.errors.IError;

/**
 * Ошибка валидации
 *
 * @see ru.bogdanov.diplom.service.validator.ValidationRunner
 */
public class CustomValidationException extends RuntimeException {

    @Getter
    private final IError error;

    public CustomValidationException(IError error) {
        super(error.getReason());
        this.error = error;
    }
}
