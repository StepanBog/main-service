package ru.bogdanov.diplom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.grpc.generated.TransactionStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью транзакции
 *
 * @see Transaction
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findAllByStatus(TransactionStatus status, Sort sort);

    @Override
    @EntityGraph(attributePaths = {"employee", "employee.requisites"})
    List<Transaction> findAll(Specification<Transaction> specification);

    @Override
    @EntityGraph(attributePaths = {"employee", "employee.requisites"})
    Page<Transaction> findAll(Specification<Transaction> specification, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"employee", "employee.requisites"})
    List<Transaction> findAll(Specification<Transaction> specification, Sort sort);

    @EntityGraph(attributePaths = {"employee", "employee.requisites", "employee.salary"})
    @Query(value = "select p from Transaction p where p.id = :transactionId")
    Optional<Transaction> findOneFull(@Param("transactionId") UUID transactionId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE transaction SET status = :status WHERE id = :id")
    void updateStatus(@Param("id") UUID id, @Param("status") String status);
}
