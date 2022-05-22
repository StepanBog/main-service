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
import java.util.Comparator;
import java.util.List;
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
    public Transaction pickUpPayment(@NotNull final UUID employeeId,
                                     long sum) {
      //  log.info("Pick up payment with sum - {}, for employeeId - {}", sum, employeeId);
        Employee employee = employeeService.findOne(employeeId);
        List<Salary> salaries = salaryService.findAllByEmployeeId(employeeId);
     //   log.info("Start pickUpPayment foe employeeId - {}", employeeId);

        Transaction transaction = Transaction.builder()
                .employee(employee)
                .approveStatus(ApproveStatus.TO_APPROVE)
                .status(TransactionStatus.NEW_TRANSACTION)
                .date(LocalDateTime.now())
                .totalSum(sum)
                .build();

        salaries.sort(Comparator.comparing(Salary::getPeriod));
        //уменьшаемая сумма транзакции
        long reducedTransactionSum = transaction.getTotalSum();
        //оставщаяся сумма выплат транзакций
        long remainingPaymentSum = reducedTransactionSum;

        validate(employee, transaction,employeeService);

        for (Salary salary : salaries) {
            reducedTransactionSum -= salary.getAvailableCash();
            /*if (reducedTransactionSum >= 0) {
                payment.setSum(salary.getAvailableCash());
            } else {
                payment.setSum(remainingPaymentSum);
            }*/

            if (reducedTransactionSum <= 0) {
                break;
            }
            remainingPaymentSum = reducedTransactionSum;
        }

        transaction.setEmployee(employee);
        transaction = transactionService.create(transaction);

       /* Map<String, Object> params = new HashMap<>();
        params.put(ProcessConstants.TRANSACTION_ID_FIELD, transaction.getId().toString());
        params.put(ProcessConstants.EMPLOYEE_ID_FIELD, employee.getId().toString());
        params.put(ProcessConstants.EMPLOYER_ID_FIELD, employee.getEmployer().getId().toString());
        params.put(ProcessConstants.EMPLOYER_INN_FIELD, employee.getEmployer().getRequisites().getInn());
        params.put(ProcessConstants.BANK_ACCOUNT_NUMBER_FIELD, employee.getRequisites().getAccountNumber());
        params.put(ProcessConstants.POSITION_ID_FIELD, employee.getPosition().getId().toString());
        params.put(ProcessConstants.TRANSACTION_SUM_FIELD, sum);
        params.put(ProcessConstants.STATUS_NAME_FIELD, TransactionStatus.NEW.name());
        params.put(ProcessConstants.TRANSACTION_COMMISSION_FIELD, transaction.getCommission());
        params.put(ProcessConstants.TRANSACTION_COMMISSION_PAYER_FIELD, transaction.getCommissionPayer());
        params.put(ProcessConstants.EXTERNAL_POSITION_ID_FIELD, employee.getPosition().getExternalPositionId());*/

        log.info("End pickUpPayment foe employeeId - {}", employeeId);
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
                          @NotNull final IEmployeeService employeeService) {
        employeeValidator.validate(employee);
        transactionValidator.validate(employee, transaction);
    }

   /* @Override
    public void approveSign(ApproveSignDocuments request) {
        try {
            processExecutionService.handleReceiveAsyncMessage(
                    request.getEntityId(),
                    ProcessConstants.DOCUMENT_SIGNED_MESSAGE_REFERENCE,
                    Map.of(
                            ProcessConstants.SIGN_APPROVE_NOTIFICATION_ID_FIELD, request.getNotificationId(),
                            ProcessConstants.TRANSACTION_SIGN_ID_FIELD, request.getSignId()
                    )
            );
        } catch (RuntimeException e) {
            log.error("Error approve sign for request - {}", request, e);
            throw new ServiceException("Error approve sign", ErrorCode.SIGN_CREATION_ERROR);
        }
    }*/

    @Override
    public void expired(@NotNull final Transaction transaction) {
        transactionService.updateStatus(transaction.getId(), TransactionStatus.TRANSACTION_EXPIRED);
    }
}
