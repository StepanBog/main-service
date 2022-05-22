package ru.bogdanov.diplom.manager.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;
import ru.bogdanov.diplom.manager.IEmployeeManager;
import ru.bogdanov.diplom.mapper.EmployeeMapper;
import ru.bogdanov.diplom.service.IEmployeeService;
import ru.bogdanov.diplom.service.IEmployerService;

/**
 * @author SBogdanov
 * Менеджер для работы с работником
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmployeeManager implements IEmployeeManager {

    private final IEmployeeService employeeService;
    private final IEmployerService employerService;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee create(Employee request) {
        Employee result;

        try {
            Employee findEmployee = employeeService.findOne(
                    request.getEmployer().getRequisites().getInn(),
                    request.getRequisites().getAccountNumber()
            );
            result =
                    employeeService.save(
                            employeeMapper.update(findEmployee, request)
            );

            return result;
        } catch (ServiceException e) {

           /* log.info("Method create. {}, save new employee", e.getMessage());
            Employee employee = employeeMapper.transformToEntity(request);*/
            request.setEmployer(employerService.findOneByInnFull(request.getEmployer().getRequisites().getInn()));
            request.setStatus(EmployeeStatus.NEW_EMPLOYEE);

            result = employeeService.save(request);
            return result;
        }
    }

    @Override
    public Employee update(Employee request) {
        Employee findEmployee = employeeService.findOneFull(request.getId());

        Employee updateEmployee = employeeMapper.update(findEmployee, request);
        return
                employeeService.save(updateEmployee);
    }
/*
    @Override
    public void approveSign(@NotNull ApproveSignDocuments request) {
        processExecutionService.handleReceiveAsyncMessage(
                request.getEmployeeId(),
                ProcessConstants.DOCUMENT_SIGNED_MESSAGE_REFERENCE,
                null
        );
    }*/
}
