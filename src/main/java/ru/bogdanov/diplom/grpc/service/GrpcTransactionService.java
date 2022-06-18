package ru.bogdanov.diplom.grpc.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import ru.bogdanov.diplom.grpc.generated.ApproveRequest;
import ru.bogdanov.diplom.grpc.generated.DeclineRequest;
import ru.bogdanov.diplom.grpc.generated.service.transaction.*;
import ru.bogdanov.diplom.manager.IPaymentTransactionManager;
import ru.bogdanov.diplom.mapper.TransactionMapper;
import ru.bogdanov.diplom.service.ITransactionService;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author SBogdanov
 * GRPC сервис для обработки запросов по сущности транзакций работника
 */
@Slf4j
@GrpcService
@AllArgsConstructor
public class GrpcTransactionService extends TransactionServiceGrpc.TransactionServiceImplBase {

    private final ITransactionService transactionService;
    private final IPaymentTransactionManager transactionManager;
    private final TransactionMapper transactionMapper;

    @Override
    public void createRequest(CreatePaymentRequest request, StreamObserver<ru.bogdanov.diplom.grpc.generated.Transaction> responseObserver) {
        ru.bogdanov.diplom.data.model.Transaction transactionEntity =
                transactionManager.createTransactionRequest(
                        UUID.fromString(request.getEmployeeId()),
                        request.getSum()
                );

        responseObserver.onNext(
                transactionMapper.transform(
                        transactionEntity,
                        transactionEntity.getEmployee().getRequisites().getAccountNumber()
                )
        );
        responseObserver.onCompleted();
    }

    @Override
    public void findOneById(FindByIdRequest request, StreamObserver<ru.bogdanov.diplom.grpc.generated.Transaction> responseObserver) {
        var transaction = transactionService.findOneFull(
                UUID.fromString(request.getTransactionId()));
        responseObserver.onNext(
                transactionMapper.transform(
                        transaction,
                        transaction.getEmployee().getRequisites().getAccountNumber()
                )
        );
        responseObserver.onCompleted();
    }

    @Override
    public void find(SearchTransactionRequest request, StreamObserver<TransactionSearchResponse> responseObserver) {
        Page<ru.bogdanov.diplom.data.model.Transaction> transactions = transactionService.findAll(request);
        responseObserver.onNext(TransactionSearchResponse.newBuilder()
                .addAllTransactions(transactions.getContent()
                        .stream()
                        .map(transaction -> transactionMapper.transform(
                                transaction,
                               null
                        ))
                        .collect(Collectors.toSet()))
                .setTotalPages(transactions.getTotalPages())
                .setTotalSize(transactions.getTotalElements())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void approveRequest(ApproveRequest request, StreamObserver<Empty> responseObserver) {
        transactionManager.approveRequest(UUID.fromString(request.getTransactionId()));
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void declineRequest(DeclineRequest request, StreamObserver<Empty> responseObserver) {
        transactionManager.declineRequest(UUID.fromString(request.getTransactionId()));
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void update(UpdateRequest request, StreamObserver<Empty> responseObserver) {
        transactionManager.withdrawn(UUID.fromString(request.getTransactionId()),request.getStatus());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
