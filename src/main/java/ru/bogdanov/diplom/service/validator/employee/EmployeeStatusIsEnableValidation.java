package ru.bogdanov.diplom.service.validator.employee;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.bogdanov.diplom.data.enums.errors.EmployeeError;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;
import ru.bogdanov.diplom.service.validator.AbstractValidator;

@Slf4j
@AllArgsConstructor
public class EmployeeStatusIsEnableValidation extends AbstractValidator<Employee> {

    @Override
    public boolean test(Employee employee) {
        if (!EmployeeStatus.ENABLED_EMPLOYEE.equals(employee.getStatus())) {
            setError(EmployeeError.EMPLOYEE_STATUS_IS_NOT_ENABLED);
        }
        return hasNoError();
    }
}
