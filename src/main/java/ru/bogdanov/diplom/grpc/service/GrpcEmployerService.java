package ru.bogdanov.diplom.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import ru.bogdanov.diplom.grpc.generated.Employer;
import ru.bogdanov.diplom.grpc.generated.service.employer.*;
import ru.bogdanov.diplom.mapper.EmployerMapper;
import ru.bogdanov.diplom.service.IEmployerService;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author SBogdanov
 * GRPC сервис для обработки запросов связанных с сущностью работодатель
 * @see ru.bogdanov.diplom.data.model.Employer
 */
@Slf4j
@GrpcService
@AllArgsConstructor
public class GrpcEmployerService extends EmployerServiceGrpc.EmployerServiceImplBase {

    private final IEmployerService employerService;
    private final EmployerMapper employerMapper;

    @Override
    public void save(Employer request, StreamObserver<Employer> responseObserver) {
        responseObserver.onNext(
                employerMapper.transform(
                        employerService.save(
                                employerMapper.transform(request)
                        )
                )
        );
        responseObserver.onCompleted();
    }

    @Override
    public void find(SearchEmployerRequest request, StreamObserver<EmployersResponse> responseObserver) {
        Page<ru.bogdanov.diplom.data.model.Employer> employers = employerService.findAll(request);
        responseObserver.onNext(EmployersResponse.newBuilder()
                .addAllEmployers(employers.getContent()
                        .stream()
                        .map(employerMapper::transform)
                        .collect(Collectors.toSet()))
                .setTotalPages(employers.getTotalPages())
                .setTotalSize(employers.getTotalElements())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void findOne(OneEmployerRequest request, StreamObserver<Employer> responseObserver) {
        UUID employeeId = UUID.fromString(request.getEmployerId());
        responseObserver.onNext(
                employerMapper.transformFull(
                        employerService.findOne(employeeId))
        );
        responseObserver.onCompleted();
    }

    @Override
    public void findOneByInn(EmployerInnRequest request, StreamObserver<Employer> responseObserver) {
        String employeeId = request.getInn();
        responseObserver.onNext(
                employerMapper.transform(
                        employerService.findOneByInn(employeeId))
        );
        responseObserver.onCompleted();
    }
}
