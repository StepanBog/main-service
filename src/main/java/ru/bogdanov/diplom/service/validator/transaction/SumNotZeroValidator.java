package ru.bogdanov.diplom.service.validator.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.bogdanov.diplom.data.enums.errors.TransactionError;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.validator.AbstractValidator;

/**
 * Проверка того что сумма выплаты не равно нулю
 */
@Slf4j
@RequiredArgsConstructor
public class SumNotZeroValidator extends AbstractValidator<Transaction> {

    @Override
    public boolean test(Transaction transaction) {
        if (transaction.getTotalSum() <= 0) {
            setError(TransactionError.TRANSACTION_SUM_NOT_MORE_ZERO);
        }
        return hasNoError();
    }
}
