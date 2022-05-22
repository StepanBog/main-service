package ru.bogdanov.diplom.data.enums.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;

/**
 * Ошибки выплаты работнику
 */
@AllArgsConstructor
@Getter
public enum TransactionError implements IError {

    TRANSACTION_SUM_IS_MORE_AVAILABLE_CASH(
            "Transaction sum is more than the available cash",
            ErrorCode.AVAILABLE_CASH_IS_EXCEED_TRANSACTION_SUM),

    TRANSACTION_SUM_IS_LESS_MINIMUM_VALUE(
            "Transaction sum is less than the minimum value",
            ErrorCode.TRANSACTION_NOT_CREATED),
    
    TRANSACTION_SUM_NOT_MORE_ZERO(
            "Transaction sum is not more than zero",
            ErrorCode.TRANSACTION_NOT_CREATED),

    TRANSACTION_SUM_IS_MORE_LIMIT_VALUE("Transaction sum is more than the limit value",
            ErrorCode.AVAILABLE_CASH_IS_EXCEED_TRANSACTION_SUM),

    TRANSACTION_ERROR_UPDATE_ID_IS_NULL(
            "Transaction update operation error. Transaction id is null",
            ErrorCode.TRANSACTION_NOT_CREATED),

    TRANSACTION_ERROR_CREATE_ID_IS_NOT_NULL(
            "Transaction create operation error. Transaction id is not null",
            ErrorCode.TRANSACTION_NOT_CREATED),

    TRANSACTION_ERROR_CREATE_WITH_INTERNAL_TYPE(
            "Transaction create operation error. Transaction type is internal",
            ErrorCode.TRANSACTION_NOT_CREATED),

    TRANSACTION_ERROR_LAST_DAY_OF_MONTH(
            "Transaction create operation error. Transaction date is last day of month",
            ErrorCode.TRANSACTION_NOT_CREATED),
    TRANSACTION_ERROR_HOLIDAY(
            "Transaction create operation error. Transaction date is holiday",
            ErrorCode.TRANSACTION_NOT_CREATED);

    private final String reason;

    private final ErrorCode code;

    @Override
    public ErrorCode getCode() {
        return code;
    }

}
