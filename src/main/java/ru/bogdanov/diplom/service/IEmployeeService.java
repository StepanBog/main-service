package ru.bogdanov.diplom.service;

import org.springframework.data.domain.Page;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;
import ru.bogdanov.diplom.grpc.generated.service.employee.SearchEmployeeRequest;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author SBogdanov
 * Сервис для работы с работниками
 */
public interface IEmployeeService {

    /**
     * Сохранение сущности работника
     */
    Employee save(@NotNull final Employee employee);

    /**
     * Удаление сущности работника
     *
     * @param id - id работника
     */
    void delete(@NotNull final UUID id);

    /**
     * Поиск сотрудников по критериям поиска
     *
     * @param searchRequest - сущность с параметрами поиска сотрудников
     * @return найденные сотрудники, либо пустой список
     */
    Page<Employee> findAll(@NotNull final SearchEmployeeRequest searchRequest);

    /**
     * Получить сотрудника по id
     *
     * @param employeeId - id сотрудника
     * @return найденный сотрудник
     */
    Employee findOne(@NotNull final UUID employeeId);

    /**
     * Поиск сотрудника со всеми связанными сущностей
     *
     * @param employeeId - id сотрудника
     * @return найденный сотрудник
     */
    Employee findOneFull(@NotNull final UUID employeeId);

    /**
     * Поиск сотрудника по лоигну и id работадателя
     *
     * @param accountNumber счет работника
     * @return найденный сотрудник
     */
    Employee findOne(@NotNull final String accountNumber);

    /**
     * Получение количества работников со определенным статусом у конкретного работодателя
     *
     * @param employer работодатель
     * @param status статус работника
     * @return количество
     */
    long countEmployeesByEmployer(@NotNull final Employer employer, @NotNull EmployeeStatus status);

    /**
     * Получение количества работников у конкретного работодателя
     *
     * @param employer работодатель
     * @return количество
     */
    long countEmployeesByEmployer(@NotNull final Employer employer);

    /**
     * Обновить статус работника по id
     *
     * @param id     - id работника
     * @param status - новый статус работника
     */
    void updateStatus(@NotNull final UUID id,
                      @NotNull final EmployeeStatus status);

    /**
     * Обновить статус у работников по id работодателя
     *
     * @param employerId - id работодателя
     * @param status     - статус работника у которого нужно обновить статус
     * @param newStatus  - новый статус работника
     */
    void updateStatusByEmployerIdAndStatus(@NotNull final UUID employerId,
                                           @NotNull final EmployeeStatus status,
                                           @NotNull final EmployeeStatus newStatus);
}
