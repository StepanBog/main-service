package ru.bogdanov.diplom.repository.specification.entity;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.bogdanov.diplom.data.enums.SearchOperation;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.grpc.generated.service.employer.SearchEmployerRequest;
import ru.bogdanov.diplom.repository.specification.JpaSpecification;
import ru.bogdanov.diplom.repository.specification.SearchCriteria;
import ru.bogdanov.diplom.utils.JpaUtils;
import ru.bogdanov.diplom.utils.TimeUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author SBogdanov
 * Создание спецификаций для сущности работодатель
 * @see Employer
 */
@UtilityClass
public class EmployerSpecification {

    public static Specification<Employer> createSpecification(@NotNull final SearchEmployerRequest searchEmployer) {
        List<Specification<Employer>> specs = new ArrayList<>(5);

        if (searchEmployer.hasEmployerId()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("id",
                                    SearchOperation.EQUALITY,
                                    UUID.fromString(searchEmployer.getEmployerId()))
                    )
            );
        }

   /*     if (searchEmployer.hasStatus()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("status",
                                    SearchOperation.EQUALITY,
                                    searchEmployer.getStatus())
                    )
            );
        }*/

        if (searchEmployer.hasName()) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("name",
                                    SearchOperation.CONTAINS,
                                    searchEmployer.getName())
                    )
            );
        }

        if (searchEmployer.hasEmail() && StringUtils.isNotEmpty(searchEmployer.getEmail())) {
            specs.add(new JpaSpecification(
                            new SearchCriteria("email",
                                    SearchOperation.EQUALITY,
                                    searchEmployer.getEmail())
                    )
            );
        }

        if (searchEmployer.hasCreatedAt()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployer.getCreatedAt()).toLocalDate(), LocalTime.MIN))));
            specs.add(new JpaSpecification(
                    new SearchCriteria("createdAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployer.getCreatedAt()).toLocalDate(), LocalTime.MAX))));
        }
        if (searchEmployer.hasUpdatedAt()) {
            specs.add(new JpaSpecification(
                    new SearchCriteria("updatedAt",
                            SearchOperation.LOCALDATETIME_GREATHER_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployer.getUpdatedAt()).toLocalDate(), LocalTime.MIN))));
            specs.add(new JpaSpecification(
                    new SearchCriteria("updatedAt",
                            SearchOperation.LOCALDATETIME_LESS_THAN,
                            LocalDateTime.of(TimeUtils.toLocalDateTime(searchEmployer.getUpdatedAt()).toLocalDate(), LocalTime.MAX))));
        }
        return JpaUtils.buildSingleAndSpecification(specs);
    }
}
