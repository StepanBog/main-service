package ru.bogdanov.diplom.service;

import ru.bogdanov.diplom.data.model.Salary;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author VKozlov
 * Сервис для работы со счетом работника
 */
public interface ISalaryService {

    /**
     * Поиск счета по id
     *
     * @param id - id счета
     * @return найденный счет
     */
    Salary findOne(@NotNull final UUID id);

    /**
     * Поиск счетаов по employee id
     *
     * @param employeeId - id работника
     * @return найденный счет
     */
    List<Salary> findAllByEmployeeId(@NotNull final UUID employeeId);


    /**
     * Поиск счетаов по position id
     *
     * @param positionId - id работника
     * @return найденный счет
     */
    List<Salary> findAllByPositionId(@NotNull UUID positionId);

    /**
     * Сохранение счета работодателя
     */
    Salary save(@NotNull final Salary salary);

    /**
     * Удаление счетов по id позиции
     *
     * @param positionId - id позиции
     */
    void deleteByPositionId(UUID positionId);

}
