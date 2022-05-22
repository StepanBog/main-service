package ru.bogdanov.diplom.data.enums.errors;

import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;

/**
 * интерфейс маркер для ошибок
 */
public interface IError {

    ErrorCode getCode();

    String getReason();
}
