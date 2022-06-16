package ru.bogdanov.diplom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.data.model.Salary;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.repository.EmployeeRepository;
import ru.bogdanov.diplom.repository.SalaryRepository;
import ru.bogdanov.diplom.service.ISalaryService;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author SBogdanov
 * Сервис для работы с выплатами работнику
 */
@Slf4j
@Service
@AllArgsConstructor
public class SalaryService implements ISalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Salary findOne(@NotNull final UUID id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        String.format("Salary nof found by id - %s", id), ErrorCode.UNKNOWN_ERROR));
    }

    @Override
    public Salary findByEmployeeId(@NotNull UUID employeeId) {
        Employee employee = employeeRepository.findOneFull(employeeId).get();
        return employee.getSalary();
    }

    @Override
    @Transactional
    public Salary save(@NotNull final Salary salary) {
        salary.setDate(LocalDate.now());
        return salaryRepository.save(salary);
    }

    @Override
    @Transactional
    public void deleteByPositionId(UUID positionId) {
        return;
    }
}
