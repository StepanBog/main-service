package ru.bogdanov.diplom.service.validator.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.bogdanov.diplom.data.enums.errors.TransactionError;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.validator.AbstractValidator;

import java.math.BigDecimal;

/**
 * Проверка того что сумма выплаты не превышает доступную сумму
 */
@Slf4j
@RequiredArgsConstructor
public class SumNotMoreAvailableCashValidator extends AbstractValidator<Transaction> {

    private final BigDecimal percentUseOfAvailableCash;
    private long availableCash = 0;

   /* public SumNotMoreAvailableCashValidator calculateAvailableCash(@NotNull final EmployeeHoldSum employeeHoldSum,
                                                                   @NotNull final List<Position> positions) {
        this.availableCash = CommonUtils.calculateTotalAvailableSum(
                percentUseOfAvailableCash,
                positions) - CommonUtils.calculateHoldSum(employeeHoldSum);
        return this;
    }

    public SumNotMoreAvailableCashValidator calculateAvailableCash(
            EmployeeHoldSum employeeHoldSum,
            @NotNull final List<Position> positions,
            @NotNull final Transaction transaction) {
        this.availableCash = CommonUtils.calculateTotalAvailableSum(percentUseOfAvailableCash,positions)
                - CommonUtils.calculateHoldSum(employeeHoldSum);

        if (!transaction.isNew()) {
            this.availableCash += transaction.getTotalSum();
        }
        return this;
    }*/

    @Override
    public boolean test(Transaction transaction) {
        if (transaction.getTotalSum() > availableCash) {
            setError(TransactionError.TRANSACTION_SUM_IS_MORE_AVAILABLE_CASH);
        }
        return hasNoError();
    }
}
