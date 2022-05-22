package ru.bogdanov.diplom.manager;

import com.sun.istack.NotNull;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.data.model.Transaction;

public interface IEmployerManager {
    /**
     * Обновить данные работодателя
     *
     * @param request - параметры нового работника
     * @return - обновленный работник
     */
    Employer update(Employer request);

    /**
     * Подтвердить транзакцию
     *
     * @param request запрос для подтверждения
     */
    void approve(@NotNull Transaction request);
}
