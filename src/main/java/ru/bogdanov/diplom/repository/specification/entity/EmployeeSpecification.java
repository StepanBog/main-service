package ru.bogdanov.diplom.repository.specification.entity;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import ru.bogdanov.diplom.data.enums.SearchOperation;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.grpc.generated.service.employee.SearchEmployeeRequest;
import ru.bogdanov.diplom.repository.specification.JpaSpecification;
import ru.bogdanov.diplom.repository.specification.SearchCriteria;
import ru.bogdanov.diplom.utils.JpaUtils;
import ru.bogdanov.diplom.utils.TimeUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author SBogdanov
 * Создание спецификаций для сущности работник
 * @see Employee
 */
@UtilityClass
public class EmployeeSpecification {

    public static Specification<Employee> createSpecification(@NotNull final SearchEmployeeRequest searchEmployee) {
        List<Specification<Employee>> specs = new ArrayList<>(5);
        specs.add((root, criteriaQuery, criteriaBuilder) -> {
            Join requisitesJoin = root.join("requisites");
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (searchEmployee.hasFirstName()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(requisitesJoin.get("firstName")),
                        "%" + searchEmployee.getFirstName().toLowerCase() + "%"));
            }

            if (searchEmployee.hasLastName()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(requisitesJoin.get("lastName")),
                        "%" + searchEmployee.getLastName().toLowerCase() + "%"));
            }

            if (searchEmployee.hasPatronymicName()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(requisitesJoin.get("patronymicName")),
                        "%" + searchEmployee.getPatronymicName().toLowerCase() + "%"));
            }

            if (searchEmployee.hasEmployerId()) {
                Join employerJoin = root.join("employer");
                employerJoin.on(criteriaBuilder.equal(employerJoin.get("id"),
                        UUID.fromString(searchEmployee.getEmployerId())));
            }

          /*  if (searchEmployee.hasInn()) {
                predicates.add(criteriaBuilder.like(requisitesJoin.get("inn"),
                        "%" + searchEmployee.getInn() + "%"));
            }*/

            if (!CollectionUtils.isEmpty(predicates)) {
                Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
                requisitesJoin.on(predicatesArray);
            }
            return criteriaQuery.getRestriction();
        });

        if (searchEmployee.hasEmployeeId()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("id",
                                    SearchOperation.EQUALITY,
                                    UUID.fromString(searchEmployee.getEmployeeId()))
                    )
            );
        }

        if (searchEmployee.hasBankAccountNumber()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("requisites.accountNumber",
                                    SearchOperation.EQUALITY,
                                    searchEmployee.getBankAccountNumber())
                    )
            );
        }

        if (searchEmployee.hasSalaryUpdatedAfterDate()) {
            specs.add(
                    new JpaSpecification(
                            new SearchCriteria(
                                    "position.salaries.salaryUpdateAt",
                                    SearchOperation.LOCALDATETIME_GREATHER_THAN,
                                    TimeUtils.toLocalDateTime(searchEmployee.getSalaryUpdatedAfterDate())
                            )
                    )
            );
        }

        if (searchEmployee.hasStatus()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("status",
                                    SearchOperation.EQUALITY,
                                    searchEmployee.getStatus())
                    )
            );
        }

        if (searchEmployee.hasPhone() && StringUtils.isNotEmpty(searchEmployee.getPhone())) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("phone",
                                    SearchOperation.EQUALITY,
                                    searchEmployee.getPhone())
                    )
            );
        }

        if (searchEmployee.hasCreatedAt()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployee.getCreatedAt()).toLocalDate(), LocalTime.MIN))));
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployee.getCreatedAt()).toLocalDate(), LocalTime.MAX))));
        }
        if (searchEmployee.hasUpdatedAt()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("updatedAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployee.getUpdatedAt()).toLocalDate(), LocalTime.MIN))));
            specs.add(new JpaSpecification(
                    new SearchCriteria("updatedAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployee.getUpdatedAt()).toLocalDate(), LocalTime.MAX))));
        }
        return JpaUtils.buildSingleAndSpecification(specs);
    }
}
