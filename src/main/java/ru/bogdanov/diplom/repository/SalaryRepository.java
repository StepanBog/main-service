package ru.bogdanov.diplom.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdanov.diplom.data.model.Salary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью счет работника
 *
 * @see Salary
 */
@Repository
public interface SalaryRepository extends JpaRepository<Salary, UUID> {
    @Query(nativeQuery = true, value = "select s from salary s where s.id = (select e.salary_id from employee e where e.id = :employeeId)")
    Salary findByEmployeeId(@Param("employeeId") UUID employeeId);

    @Query(value = "select s from Salary s where s.id = :id")
    Optional<Salary> findByIdFull(@Param("id") UUID id);

    @Override
    @EntityGraph(attributePaths = {"position"})
    List<Salary> findAll();

    @Override
    @EntityGraph(attributePaths = {"position"})
    Optional<Salary> findById(UUID uuid);
}
