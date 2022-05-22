package ru.bogdanov.diplom.service.validator;

import ru.bogdanov.diplom.data.exception.CustomValidationException;

import java.util.function.Consumer;

public final class ValidationRunner<T> implements Consumer<T> {

    private final AbstractValidator<T>[] validators;

    public ValidationRunner(AbstractValidator<T>... validators) {
        this.validators = validators;
    }

    @Override
    public void accept(T t) {
        for (AbstractValidator<T> validator : validators) {
            if (!validator.test(t)) {
                throw new CustomValidationException(validator.getError());
            }
        }
    }
}
