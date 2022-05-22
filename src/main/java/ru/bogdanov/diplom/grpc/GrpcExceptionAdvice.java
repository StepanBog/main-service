package ru.bogdanov.diplom.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.TransactionSystemException;
import ru.bogdanov.diplom.data.exception.CustomValidationException;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.utils.GrpcUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@GrpcAdvice
public class GrpcExceptionAdvice {

    @Value(value = "${spring.application.name:odp-service}")
    private String applicationName;

    @GrpcExceptionHandler(ServiceException.class)
    public StatusRuntimeException handle(ServiceException e) {
        log.error("ServiceException exception", e);
        return StatusProto.toStatusRuntimeException(
                GrpcUtils.wrapErrorMessage(e, e.getErrorCode(), applicationName)
        );
    }

    @GrpcExceptionHandler(StatusRuntimeException.class)
    public StatusRuntimeException handle(StatusRuntimeException ex) {
        log.error("StatusRuntimeException exception", ex);
        if (ex.getStatus().getCode() != Status.INTERNAL.getCode()) {
            return StatusProto.toStatusRuntimeException(
                    GrpcUtils.wrapErrorMessage(ex, ErrorCode.UNKNOWN_ERROR, applicationName)
            );
        }
        return ex;
    }

    @GrpcExceptionHandler(CustomValidationException.class)
    public StatusRuntimeException handle(CustomValidationException e) {
        log.error("CustomValidationException exception", e);
        return StatusProto.toStatusRuntimeException(
                GrpcUtils.wrapErrorMessage(e, e.getError().getCode(), applicationName)
        );
    }

    @GrpcExceptionHandler(Exception.class)
    public StatusRuntimeException handle(Exception e) {
        log.error("Unknown exception", e);
        return StatusProto.toStatusRuntimeException(
                GrpcUtils.wrapErrorMessage(e, ErrorCode.UNKNOWN_ERROR, applicationName)
        );
    }

    @GrpcExceptionHandler(TransactionSystemException.class)
    public StatusRuntimeException handle(TransactionSystemException e) {
        log.error("Exception", e);

        Throwable cause = e.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException consEx = (ConstraintViolationException) cause;
            final String errors = consEx.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            log.error("ConstraintViolationException. Errors message - {}", errors, e);
        }
        return StatusProto.toStatusRuntimeException(
                GrpcUtils.wrapErrorMessage(e, ErrorCode.UNKNOWN_ERROR, applicationName)
        );
    }
}
