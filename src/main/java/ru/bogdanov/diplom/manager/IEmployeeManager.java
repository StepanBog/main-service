package ru.bogdanov.diplom.manager;

import ru.bogdanov.diplom.data.model.Employee;

/**
 * @author SBogdanov
 * Менеджер для работы с работником
 */
public interface IEmployeeManager {

    /**
     * Создать нового работника если он не найден
     * если работник найден то вернуть существеющего работника
     *
     * @param request - параметры нового работника
     * @return - созданный новый работник
     */
    Employee create(Employee request);

    /**
     * Обновить данные работника
     *
     * @param request - параметры нового работника
     * @return - обновленный работник
     */
    Employee update(Employee request);

  /*  *//**
     * Подтвердить подпись сотрудника
     *
     * @param request запрос для подтверждения
     *//*
    void approveSign(@NotNull ApproveSignDocuments request);*/
}
