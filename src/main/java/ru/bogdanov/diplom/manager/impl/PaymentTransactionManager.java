package ru.bogdanov.diplom.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Salary;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.grpc.generated.TransactionStatus;
import ru.bogdanov.diplom.grpc.generated.common.ApproveStatus;
import ru.bogdanov.diplom.manager.IPaymentTransactionManager;
import ru.bogdanov.diplom.service.IEmployeeService;
import ru.bogdanov.diplom.service.ISalaryService;
import ru.bogdanov.diplom.service.ITransactionService;
import ru.bogdanov.diplom.service.validator.EmployeeValidator;
import ru.bogdanov.diplom.service.validator.TransactionValidator;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author SBogdanov
 * Менеджер для работы с транзакциями работника
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentTransactionManager implements IPaymentTransactionManager {

    private final ITransactionService transactionService;
    private final IEmployeeService employeeService;
    private final ISalaryService salaryService;
    private final TransactionValidator transactionValidator;
    private final EmployeeValidator employeeValidator;


    @Override
    public Transaction createTransactionRequest(@NotNull final UUID employeeId,
                                                long sum) {
        Employee employee = employeeService.findOne(employeeId);
        Salary salary = salaryService.findByEmployeeId(employeeId);

        Transaction transaction = Transaction.builder()
                .employee(employee)
                .approveStatus(ApproveStatus.TO_APPROVE)
                .status(TransactionStatus.NEW_TRANSACTION)
                .date(LocalDateTime.now())
                .totalSum(sum)
                .build();

        validate(employee, transaction, salary, transactionService);

        transaction.setEmployee(employee);
        transaction.setStatus(TransactionStatus.AWAITING_CONFIRMATION);
        transaction = transactionService.create(transaction);

        salary.setAvailableCash(salary.getAvailableCash() - transaction.getTotalSum());
        salaryService.save(salary);

        return transaction;
    }

    /**
     * Валидация транзакции
     *
     * @param employee    - работник
     * @param transaction - транзакция работника
     */
    private void validate(@NotNull final Employee employee,
                          @NotNull final Transaction transaction,
                          @NotNull final Salary salary,
                          @NotNull final ITransactionService transactionService) {
        employeeValidator.validate(employee);
        transactionValidator.validate(transaction, salary, transactionService);
    }

    @Override
    public void approveRequest(@NotNull final UUID transactionId) {
        Transaction transaction = transactionService.findOneFull(transactionId);
        transaction.setStatus(TransactionStatus.PROCESSING);
        // execute transaction
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionService.save(transaction);
    }

    @Override
    public void declineRequest(UUID transactionId) {
        Transaction transaction = transactionService.findOneFull(transactionId);
        transaction.setStatus(TransactionStatus.DECLINE);
        Salary salary = salaryService.findByEmployeeId(transaction.getEmployee().getId());
        salary.setAvailableCash(salary.getAvailableCash() + transaction.getTotalSum());
        salaryService.save(salary);
        transactionService.save(transaction);
    }

    @Override
    public void expired(@NotNull final Transaction transaction) {
        transactionService.updateStatus(transaction.getId(), TransactionStatus.TRANSACTION_EXPIRED);
    }
}
