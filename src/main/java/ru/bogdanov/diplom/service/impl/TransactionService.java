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
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.grpc.generated.TransactionStatus;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.grpc.generated.service.transaction.SearchTransactionRequest;
import ru.bogdanov.diplom.repository.TransactionRepository;
import ru.bogdanov.diplom.repository.specification.entity.TransactionSpecification;
import ru.bogdanov.diplom.service.ITransactionService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author SBogdanov
 * Сервис для работы с транзакциями работника
 */
@Slf4j
@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction findOne(@NotNull UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                                String.format("Transaction nof found by id - %s", id),
                                ErrorCode.TRANSACTION_NOT_FOUND
                        )
                );
    }


    @Override
    public List<Transaction> findAllByStatus(TransactionStatus transactionStatus) {
        return transactionRepository.findAllByStatus(transactionStatus, Sort.by(Sort.Direction.ASC, "createdAt"));
    }

    @Override
    public Transaction findOneFull(@NotNull UUID id) {
        return transactionRepository.findOneFull(id)
                .orElseThrow(() -> new ServiceException(
                        String.format("Transaction nof found by id - %s", id),
                        ErrorCode.TRANSACTION_NOT_FOUND)
                );
    }

    @Override
    @Transactional
    public Transaction save(@NotNull Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction create(@NotNull Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void updateStatus(@NotNull UUID id, @NotNull TransactionStatus status) {
        transactionRepository.updateStatus(id, status.name());
    }

    @Override
    public Page<Transaction> findAll(@NotNull final SearchTransactionRequest searchRequest) {
        Specification<Transaction> specification = TransactionSpecification.createSpecification(searchRequest);

        if (searchRequest.hasPageNumber()
                && searchRequest.hasPageSize()) {
            return transactionRepository.findAll(
                    specification,
                    PageRequest.of(
                            searchRequest.getPageNumber(),
                            searchRequest.getPageSize()
                    ).withSort(Sort.Direction.DESC, "createdAt"));
        }

        List<Transaction> findPayments = transactionRepository.findAll(
                specification,
                Sort.by(Sort.Direction.DESC, "createdAt"));
        return new PageImpl(findPayments);
    }

}
