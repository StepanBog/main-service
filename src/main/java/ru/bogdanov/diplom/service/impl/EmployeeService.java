package ru.bogdanov.diplom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.grpc.generated.service.employee.SearchEmployeeRequest;
import ru.bogdanov.diplom.repository.EmployeeRepository;
import ru.bogdanov.diplom.repository.specification.entity.EmployeeSpecification;
import ru.bogdanov.diplom.service.IEmployeeService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author SBogdanov
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee save(@NotNull final Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Employee nof found by id " + id,
                        ErrorCode.EMPLOYEE_NOT_FOUND)
                );
        employee.setStatus(EmployeeStatus.ARCHIVED_EMPLOYEE);
        employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> findAll(@NotNull final SearchEmployeeRequest searchRequest) {
        Specification<Employee> specification = EmployeeSpecification.createSpecification(searchRequest);

        if (searchRequest.hasPageNumber()
                && searchRequest.hasPageSize()) {
            return employeeRepository.findAll(
                    specification,
                    PageRequest.of(
                            searchRequest.getPageNumber(),
                            searchRequest.getPageSize()
                    ).withSort(Sort.Direction.DESC, "createdAt"));
        }

        List<Employee> findEmployers = employeeRepository.findAll(
                specification,
                Sort.by(Sort.Direction.DESC, "createdAt"));
        return new PageImpl(findEmployers);
    }

    @Override
    public Employee findOne(@NotNull String accountNumber) {
        return employeeRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ServiceException(
                                String.format("Employee nof found by accountNumber %s",
                                        accountNumber),
                                ErrorCode.EMPLOYEE_NOT_FOUND
                        )
                );
    }

    @Override
    public long countEmployeesByEmployer(@NotNull Employer employer, @NotNull EmployeeStatus status) {
        return employeeRepository.countEmployeesByEmployerAndStatus(employer, status);
    }

    @Override
    public long countEmployeesByEmployer(@NotNull Employer employer) {
        return employeeRepository.countEmployeesByEmployer(employer);
    }

    @Override
    public Employee findOne(@NotNull final UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                                String.format("Employee nof found id %s", id), ErrorCode.EMPLOYEE_NOT_FOUND
                        )
                );
    }

    @Override
    public Employee findOneFull(@NotNull UUID id) {
        return employeeRepository.findOneFull(id)
                .orElseThrow(() -> new ServiceException(
                                String.format("Employee nof found id %s", id), ErrorCode.EMPLOYEE_NOT_FOUND
                        )
                );
    }

    @Override
    @Transactional
    public void updateStatus(@NotNull final UUID id, @NotNull final EmployeeStatus status) {
        employeeRepository.updateStatus(id, status.name());
    }

    @Override
    @Transactional
    public void updateStatusByEmployerIdAndStatus(@NotNull final UUID employerId,
                                                  @NotNull final EmployeeStatus status,
                                                  @NotNull final EmployeeStatus newStatus) {
        employeeRepository.updateStatusByEmployerIdAndStatus(employerId,
                status.name(),
                newStatus.name());
    }
}
