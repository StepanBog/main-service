package ru.bogdanov.diplom.service;

import org.springframework.data.domain.Page;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.grpc.generated.TransactionStatus;
import ru.bogdanov.diplom.grpc.generated.service.transaction.SearchTransactionRequest;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author VKozlov
 * Сервис для работы с транзакциями
 */
public interface ITransactionService {

    /**
     * Поиск транзакций по id
     *
     * @param id - id транзакции
     * @return найденная транзакция
     */
    Transaction findOne(@NotNull final UUID id);

    /**
     * Поиск транзакций со всеми связанными сущностей по id
     *
     * @param id - id транзакции
     * @return найденная транзакция
     */
    Transaction findOneFull(@NotNull final UUID id);

    /**
     * Сохранение транзакции работника
     */
    Transaction save(@NotNull final Transaction transaction);

    /**
     * Создание транзакции работника
     */
    Transaction create(@NotNull final Transaction transaction);

    /**
     * Обновить статус транзакции работника по id
     *
     * @param id     - id транзакции
     * @param status - новый статус транзакции
     */
    void updateStatus(@NotNull final UUID id,
                      @NotNull final TransactionStatus status);

    /**
     * Поиск транзакций по критериям поиска
     *
     * @param searchRequest - сущность с параметрами поиска
     * @return страница с найденными транзакциями
     */
    Page<Transaction> findAll(@NotNull final SearchTransactionRequest searchRequest);

    /**
     * Найти все транзакции по статусу
     *
     * @param status - статус транзакции
     * @return - пайденный список транзакций
     */
    List<Transaction> findAllByStatus(TransactionStatus status);
}
