package ru.bogdanov.diplom.data.exception;

import lombok.Getter;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;

/**
 * @author SBogdanov
 */
@Getter
public class ServiceException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
