package ru.bogdanov.diplom.grpc.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import ru.bogdanov.diplom.grpc.generated.Employee;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;
import ru.bogdanov.diplom.grpc.generated.Employer;
import ru.bogdanov.diplom.grpc.generated.service.employee.*;
import ru.bogdanov.diplom.manager.IEmployeeManager;
import ru.bogdanov.diplom.mapper.EmployeeMapper;
import ru.bogdanov.diplom.mapper.EmployerMapper;
import ru.bogdanov.diplom.service.IEmployeeService;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author SBogdanov
 * GRPC сервис для обработки запросов связанных с сущностью работник
 */
@Slf4j
@GrpcService
@AllArgsConstructor
public class GrpcEmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    private final IEmployeeService employeeService;
    private final IEmployeeManager employeeManager;
    private final EmployeeMapper employeeMapper;
    private final EmployerMapper employerMapper;

    @Override
    public void create(Employee request, StreamObserver<Employee> responseObserver) {
        responseObserver.onNext(employeeMapper.transform(employeeManager.create(employeeMapper.transformToEntity(request))));
        responseObserver.onCompleted();
    }

    @Override
    public void update(Employee request, StreamObserver<Employee> responseObserver) {
        ru.bogdanov.diplom.data.model.Employee employee = employeeMapper.transformToEntity(request);
        employee.setId(UUID.fromString(request.getId()));
        responseObserver.onNext(employeeMapper.transform(employeeManager.update(employee)));
        responseObserver.onCompleted();
    }

    @Override
    public void find(SearchEmployeeRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        Page<ru.bogdanov.diplom.data.model.Employee> employees = employeeService.findAll(request);

        responseObserver.onNext(EmployeesResponse.newBuilder()
                .addAllEmployees(employees.getContent()
                        .stream()
                        .map(employeeMapper::transformFull)
                        .collect(Collectors.toSet()))

                .setTotalPages(employees.getTotalPages())
                .setTotalSize(employees.getTotalElements())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void findOneById(FindOneByIdEmployeeRequest request, StreamObserver<Employee> responseObserver) {
        UUID employeeId = UUID.fromString(request.getId());
        ru.bogdanov.diplom.data.model.Employee employee = employeeService.findOne(employeeId);
        Employee result = employeeMapper.transform(employee)
                .toBuilder()
                .setEmployer(
                        employerMapper.transformWithRequisites(employee.getEmployer())
                )
                .build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void countEmployeesByEmployer(CountEmployeesByEmployerRequest request,
                                         StreamObserver<CountEmployeesResponse> responseObserver) {
        Employer employer = request.getEmployer();

        long result = request.hasStatus()
                ? employeeService.countEmployeesByEmployer(employerMapper.transform(employer), request.getStatus())
                : employeeService.countEmployeesByEmployer(employerMapper.transform(employer));

        responseObserver.onNext(CountEmployeesResponse.newBuilder().setCount(result).build());
        responseObserver.onCompleted();
    }

    @Override
    public void activateEmployee(ActivateRequest request, StreamObserver<Empty> responseObserver) {
        ru.bogdanov.diplom.data.model.Employee employee = employeeService.findOne(UUID.fromString(request.getEmployeeId()));
        employee.setStatus(EmployeeStatus.ENABLED_EMPLOYEE);
        employeeService.save(employee);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
