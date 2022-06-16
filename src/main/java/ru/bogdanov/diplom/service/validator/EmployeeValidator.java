package ru.bogdanov.diplom.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.service.validator.employee.EmployeeStatusIsEnableValidation;

import javax.validation.constraints.NotNull;

/**
 * Валидатор для работника
 *
 * @author SBogdanov
 */
@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    public void validate(@NotNull final Employee employee) {

        //валидация проверки статуса работника
        new ValidationRunner<Employee>(
                new EmployeeStatusIsEnableValidation()
        ).accept(employee);

    }
}
