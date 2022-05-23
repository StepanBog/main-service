package ru.bogdanov.diplom.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.bogdanov.diplom.grpc.generated.Salary;
import ru.bogdanov.diplom.grpc.generated.service.salary.FindSalaryByEmployeeRequest;
import ru.bogdanov.diplom.grpc.generated.service.salary.FindSalaryByIdRequest;
import ru.bogdanov.diplom.grpc.generated.service.salary.SalaryResponse;
import ru.bogdanov.diplom.grpc.generated.service.salary.SalaryServiceGrpc;
import ru.bogdanov.diplom.manager.ISalaryManager;
import ru.bogdanov.diplom.mapper.SalaryMapper;
import ru.bogdanov.diplom.service.ISalaryService;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author SBogdanov
 * GRPC сервис для обработки запросов по сущности счета работника
 */
@Slf4j
@GrpcService
@AllArgsConstructor
public class GrpcSalaryService extends SalaryServiceGrpc.SalaryServiceImplBase {

    private final ISalaryService salaryService;
    private final ISalaryManager salaryManager;
    private final SalaryMapper salaryMapper;

    @Override
    public void findOneById(FindSalaryByIdRequest request, StreamObserver<Salary> responseObserver) {
        responseObserver.onNext(
                salaryMapper.transform(
                        salaryService.findOne(
                                UUID.fromString(request.getId()))
                )
        );
        responseObserver.onCompleted();
    }

    /*@Override
    public void findTotal(FindSalaryByEmployeeRequest request, StreamObserver<Salary> responseObserver) {
        responseObserver.onNext(salaryMapper.transform(salaryManager.calculateTotalEmployeeSalary(request)));
        responseObserver.onCompleted();
    }*/

    @Override
    public void findByEmployeeId(FindSalaryByEmployeeRequest request, StreamObserver<SalaryResponse> responseObserver) {
        responseObserver.onNext(
                SalaryResponse.newBuilder().setSalary(salaryMapper.transform(
                                salaryService.findByEmployeeId(
                                        UUID.fromString(request.getEmployeeId()))))
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void save(Salary request, StreamObserver<Salary> responseObserver) {
        responseObserver.onNext(
                salaryMapper.transform(
                        salaryService.save(
                                salaryMapper.transformToEntity(request)
                        )
                )
        );
        responseObserver.onCompleted();
    }
}
