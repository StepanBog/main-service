package ru.bogdanov.diplom.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.validator.transaction.SumNotZeroValidator;

import javax.validation.constraints.NotNull;

/**
 * Валидатор для транзакции
 *
 * @author VKozlov
 */
@Component
@RequiredArgsConstructor
public class TransactionValidator {

    public void validate(@NotNull final Employee employee,
                         @NotNull final Transaction transaction){
        new ValidationRunner<Transaction>(
                new SumNotZeroValidator()
         /*       new SumNotMoreSinglePaymentValueLimitValidation(tariff.getMaxAmount()),
                new SumNotLessSinglePaymentValueMinimumValidation(tariff.getMinAmount()),
                new SumNotMoreAvailableCashValidator(BigDecimal.valueOf(tariff.getWithdrawalPercentage()))
                        .calculateAvailableCash(employee.getEmployeeHoldSum(),positions , transaction)*/
        ).accept(transaction);
    }
}
