package ru.bogdanov.diplom.service.validator;

import lombok.Setter;
import ru.bogdanov.diplom.data.enums.errors.IError;

import java.util.function.Predicate;

public abstract class AbstractValidator<T> implements Predicate<T> {

    @Setter
    private IError error;

    public boolean hasNoError() {
        return error == null;
    }

    public IError getError() {
        return error;
    }
}
