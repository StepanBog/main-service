package ru.bogdanov.diplom.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(nativeQuery = true, value = "SELECT s.* FROM salary s \n" +
            "LEFT JOIN position p on p.id = s.position_id \n" +
            "LEFT JOIN employee e on e.id = p.employee_id \n" +
            "WHERE e.id = :employeeId")
    List<Salary> findByEmployeeId(@Param("employeeId") UUID employeeId);

    @Query(nativeQuery = true, value = "SELECT s.* FROM salary s \n" +
            "WHERE s.position_id = :positionId \n" +
            "ORDER BY s.period DESC")
    List<Salary> findByPositionId(@Param("positionId") UUID positionId);

    @EntityGraph(attributePaths = {"position"})
    @Query(value = "select s from Salary s where s.id = :id")
    Optional<Salary> findByIdFull(@Param("id") UUID id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM salary WHERE position_id = :positionId")
    void deleteByPositionId(@Param("positionId") UUID positionId);

    @Override
    @EntityGraph(attributePaths = {"position"})
    List<Salary> findAll();

    @Override
    @EntityGraph(attributePaths = {"position"})
    Optional<Salary> findById(UUID uuid);
}
