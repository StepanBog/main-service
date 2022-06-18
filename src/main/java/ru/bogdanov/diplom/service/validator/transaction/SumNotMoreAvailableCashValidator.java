package ru.bogdanov.diplom.service.validator.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.bogdanov.diplom.data.enums.errors.TransactionError;
import ru.bogdanov.diplom.data.model.Salary;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.validator.AbstractValidator;

/**
 * Проверка того что сумма выплаты не превышает доступную сумму
 */
@Slf4j
@RequiredArgsConstructor
public class SumNotMoreAvailableCashValidator extends AbstractValidator<Transaction> {

    private final Salary salary;

    @Override
    public boolean test(Transaction transaction) {
        if (transaction.getTotalSum() > salary.getAvailableCash()) {
            setError(TransactionError.TRANSACTION_SUM_IS_MORE_AVAILABLE_CASH);
        }
        return hasNoError();
    }
}
