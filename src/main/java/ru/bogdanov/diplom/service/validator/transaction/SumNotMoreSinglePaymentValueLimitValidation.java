package ru.bogdanov.diplom.service.validator.transaction;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.bogdanov.diplom.data.enums.errors.TransactionError;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.validator.AbstractValidator;

/**
 * Проверка того что сумма выплаты не превышает максимальную допустимую сумму
 */
@Slf4j
@AllArgsConstructor
public class SumNotMoreSinglePaymentValueLimitValidation extends AbstractValidator<Transaction> {

    private final long limitCashValueOfSinglePayment;

    @Override
    public boolean test(Transaction transaction) {
        if (transaction.getTotalSum() > limitCashValueOfSinglePayment) {
            setError(TransactionError.TRANSACTION_SUM_IS_MORE_LIMIT_VALUE);
        }
        return hasNoError();
    }
}
