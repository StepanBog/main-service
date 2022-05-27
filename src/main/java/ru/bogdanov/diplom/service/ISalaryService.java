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
     * Поиск счета по employee id
     *
     * @param employeeId - id работника
     * @return найденный счет
     */
    Salary findByEmployeeId(@NotNull final UUID employeeId);

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
