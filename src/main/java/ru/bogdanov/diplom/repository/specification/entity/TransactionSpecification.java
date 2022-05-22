package ru.bogdanov.diplom.repository.specification.entity;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.bogdanov.diplom.data.enums.SearchOperation;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.grpc.generated.service.transaction.SearchTransactionRequest;
import ru.bogdanov.diplom.repository.specification.JpaSpecification;
import ru.bogdanov.diplom.repository.specification.SearchCriteria;
import ru.bogdanov.diplom.utils.JpaUtils;
import ru.bogdanov.diplom.utils.TimeUtils;

import javax.persistence.criteria.Join;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author SBogdanov
 * Создание спецификаций для сущности транзакция
 * @see Transaction
 */
@UtilityClass
public class TransactionSpecification {

    public static Specification<Transaction> createSpecification(@NotNull final SearchTransactionRequest searchTransaction) {
        List<Specification<Transaction>> specs = new ArrayList<>(6);

        specs.add((root, criteriaQuery, criteriaBuilder) -> {

            Join employeeJoin = root.join("employee");

            if (searchTransaction.hasEmployeeId()) {
                employeeJoin.on(criteriaBuilder.equal(employeeJoin.get("id"),
                        UUID.fromString(searchTransaction.getEmployeeId())));
            }

            if (searchTransaction.hasEmployerId()) {
                Join employerJoin = employeeJoin.join("employer");
                employerJoin.on(criteriaBuilder.equal(employerJoin.get("id"),
                        UUID.fromString(searchTransaction.getEmployerId())));
            }

            if (searchTransaction.hasBankAccountNumber()) {
                Join employeeRequisitesJoin = employeeJoin.join("requisites");
                employeeRequisitesJoin.on(criteriaBuilder.equal(employeeRequisitesJoin.get("accountNumber"),
                        searchTransaction.getBankAccountNumber()));
            }

            return criteriaQuery.getRestriction();
        });

        if (searchTransaction.hasId() && StringUtils.isNotEmpty(searchTransaction.getId())) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("id",
                                    SearchOperation.EQUALITY,
                                    UUID.fromString(searchTransaction.getId()))
                    )
            );
        }
        if (searchTransaction.hasStatus()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("status",
                                    SearchOperation.EQUALITY,
                                    searchTransaction.getStatus())
                    )
            );
        }
        if (searchTransaction.hasSum()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("totalSum",
                                    SearchOperation.EQUALITY,
                                    searchTransaction.getSum())
                    )
            );
        }

        if (searchTransaction.hasCommission()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("commission",
                                    SearchOperation.EQUALITY,
                                    searchTransaction.getCommission())
                    )
            );
        }

        if (searchTransaction.hasFromDate()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            TimeUtils.toLocalDateTime(searchTransaction.getFromDate()))));
        }

        if (searchTransaction.hasToDate()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            TimeUtils.toLocalDateTime(searchTransaction.getToDate()))));
        }
        if (searchTransaction.hasCreatedAt()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchTransaction.getCreatedAt()).toLocalDate(), LocalTime.MIN))));
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchTransaction.getCreatedAt()).toLocalDate(), LocalTime.MAX))));
        }
        if (searchTransaction.hasUpdatedAt()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("updatedAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchTransaction.getCreatedAt()).toLocalDate(), LocalTime.MIN))));
            specs.add(new JpaSpecification(
                    new SearchCriteria("updatedAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchTransaction.getCreatedAt()).toLocalDate(), LocalTime.MAX))));
        }
        return JpaUtils.buildSingleAndSpecification(specs);
    }
}
