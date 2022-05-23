package ru.bogdanov.diplom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.data.model.Salary;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.repository.SalaryRepository;
import ru.bogdanov.diplom.service.ISalaryService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author VKozlov
 * Сервис для работы с выплатами работнику
 */
@Slf4j
@Service
@AllArgsConstructor
public class SalaryService implements ISalaryService {

    private final SalaryRepository salaryRepository;

    @Override
    public Salary findOne(@NotNull final UUID id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        String.format("Salary nof found by id - %s", id), ErrorCode.UNKNOWN_ERROR));
    }

    @Override
    public Salary findByEmployeeId(@NotNull UUID employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Salary> findAllByPositionId(@NotNull UUID positionId) {
        return salaryRepository.findByPositionId(positionId);
    }

    @Override
    @Transactional
    public Salary save(@NotNull final Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    @Transactional
    public void deleteByPositionId(UUID positionId) {
        salaryRepository.deleteByPositionId(positionId);
    }
}
