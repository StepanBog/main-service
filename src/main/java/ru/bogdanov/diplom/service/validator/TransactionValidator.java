package ru.bogdanov.diplom.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Salary;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.service.ITransactionService;
import ru.bogdanov.diplom.service.validator.transaction.SumNotMoreAvailableCashValidator;
import ru.bogdanov.diplom.service.validator.transaction.SumNotZeroValidator;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Валидатор для транзакции
 *
 * @author VKozlov
 */
@Component
@RequiredArgsConstructor
public class TransactionValidator {

    public void validate(@NotNull final Transaction transaction,
                         @NotNull final Salary salary,
                         @NotNull final ITransactionService transactionService) {
        new ValidationRunner<>(
                new SumNotZeroValidator(),
                new SumNotMoreAvailableCashValidator(salary)
        ).accept(transaction);
    }
}
