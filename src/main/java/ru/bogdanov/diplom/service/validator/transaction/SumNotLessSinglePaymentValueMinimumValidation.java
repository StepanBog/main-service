package ru.bogdanov.diplom.service.validator.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.bogdanov.diplom.data.enums.errors.TransactionError;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.validator.AbstractValidator;


/**
 * Проверка того что сумма выплаты не меньше минимальной допустимой суммы
 */
@Slf4j
@AllArgsConstructor
public class SumNotLessSinglePaymentValueMinimumValidation extends AbstractValidator<Transaction> {

    private final long limitCashValueOfSinglePayment;

    @Override
    public boolean test(Transaction transaction) {
        if (transaction.getTotalSum() < limitCashValueOfSinglePayment) {
            setError(TransactionError.TRANSACTION_SUM_IS_LESS_MINIMUM_VALUE);
        }
        return hasNoError();
    }
}
