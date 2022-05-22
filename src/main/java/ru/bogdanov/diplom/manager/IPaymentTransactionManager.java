package ru.bogdanov.diplom.manager;

import ru.bogdanov.diplom.data.model.Transaction;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author SBogdanov
 * Менеджер для работы с выплатами работнику
 */
public interface IPaymentTransactionManager {

    /**
     * Запрос выплаты работником
     *
     * @param employeeId id работника
     * @param sum        сумма выплаты
     */
    Transaction pickUpPayment(@NotNull final UUID employeeId,
                              @NotNull long sum);

   /* *//**
     * Подтвердить транзакцию выплаты
     *
     * @param request запрос для подтверждения
     *//*
    void approveSign(@NotNull ApproveSignDocuments request);*/

    /**
     * Транзакция просрочена т.к. просрочена подпись и уведомление подтверждающее подпись
     *
     * @param transaction сущность транзакции
     */
    void expired(@NotNull final Transaction transaction);
}
