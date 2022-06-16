package ru.bogdanov.diplom.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdanov.diplom.data.model.Employer;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью работодатель
 *
 * @see Employer
 */
@Repository
public interface EmployerRepository extends JpaRepository<Employer, UUID>, JpaSpecificationExecutor<Employer> {

    @Query(value = "select e from Employer e where e.requisites.inn = :employerInn")
    Optional<Employer> findByInn(@Param("employerInn") String inn);

    @EntityGraph(attributePaths = {"requisites",})
    @Query(value = "select e from Employer e where e.requisites.inn = :employerInn")
    Optional<Employer> findByInnFull(@Param("employerInn") String inn);

    @Override
    @EntityGraph(attributePaths = {"requisites", "contacts"})
    Optional<Employer> findById(UUID employerId);

}
