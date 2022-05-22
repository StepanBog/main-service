package ru.bogdanov.diplom.utils;

import com.google.protobuf.Any;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.AbstractBlockingStub;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.grpc.generated.error.ErrorInfoCustom;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author SBogdanov
 * Утилиный класс для работы с grpc
 */
@UtilityClass
public class GrpcUtils {

    private final long DEFAULT_DURATION = 3000L;

    public static <T extends AbstractBlockingStub<T>> T wrapClient(AbstractBlockingStub<T> blockingStub) {
        return blockingStub.withCompression("gzip")
                .withWaitForReady()
                .withDeadlineAfter(DEFAULT_DURATION, TimeUnit.MILLISECONDS);
    }

    public static <T extends AbstractBlockingStub<T>> T wrapClient(AbstractBlockingStub<T> blockingStub, long duration) {
        return blockingStub.withCompression("gzip")
                .withWaitForReady()
                .withDeadlineAfter(duration, TimeUnit.MILLISECONDS);
    }

    public static <R> R wrapError(Supplier<R> supplier, ErrorCode errorCode) {
        try {
            return supplier.get();
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() != Status.INTERNAL.getCode()) {
                throw new ServiceException(e.getMessage(), errorCode);
            }
            throw e;
        }
    }


    public com.google.rpc.Status wrapErrorMessage(@NotNull final Exception e,
                                                  @NotNull final ErrorCode errorCode,
                                                  @NotNull final String domain,
                                                  String errorMessage) {
        ErrorInfoCustom errorInfo = ErrorInfoCustom.newBuilder()
                .setReason(StringUtils.isEmpty(e.getMessage()) ? "" : e.getMessage())
                .setDomain(domain)
                .setErrorCode(errorCode.getNumber())
                .build();

        com.google.rpc.Status.Builder builder = com.google.rpc.Status.newBuilder()
                .setCode(Status.INTERNAL.getCode().value())
                .addDetails(Any.pack(errorInfo));

        if (!StringUtils.isEmpty(errorMessage)) {
            builder.setMessage(errorMessage);
        }
        return builder.build();
    }

    public com.google.rpc.Status wrapErrorMessage(Exception e,
                                                  ErrorCode errorCode,
                                                  String domain) {
        return wrapErrorMessage(e, errorCode, domain, null);
    }
}
