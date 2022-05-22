package ru.bogdanov.diplom.data.enums.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;

/**
 * Ошибки работника
 */
@AllArgsConstructor
@Getter
public enum EmployeeError implements IError {

    EMPLOYEE_STATUS_IS_NOT_ENABLED("Employee status is not ENABLED", ErrorCode.SERVICE_IS_UNAVAILABLE),
    EMPLOYEE_NOT_CHECKED_BY_LIST("Employee in blackList", ErrorCode.SERVICE_IS_UNAVAILABLE_USER_BLOCKED);

    private final String reason;

    private final ErrorCode code;

    @Override
    public ErrorCode getCode() {
        return code;
    }
}
