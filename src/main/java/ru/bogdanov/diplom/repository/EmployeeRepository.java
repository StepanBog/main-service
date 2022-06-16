package ru.bogdanov.diplom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью работник
 *
 * @see Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID>, JpaSpecificationExecutor<Employee> {

    long countEmployeesByEmployerAndStatus(Employer employer, EmployeeStatus status);

    long countEmployeesByEmployer(Employer employer);

    @EntityGraph(attributePaths = {"employer", "requisites", "salary"})
    @Query(value = "select e from Employee e where e.employer.id = :employerId")
    List<Employee> findByEmployerId(@Param("employerId") UUID employerId);

    @Override
    @EntityGraph(attributePaths = {"employer", "employer.requisites", "requisites", "salary"})
    Optional<Employee> findById(UUID uuid);

    @Override
    @EntityGraph(attributePaths = {"employer", "employer.requisites", "requisites", "salary"})
    List<Employee> findAll(Specification<Employee> specification);

    @Override
    @EntityGraph(attributePaths = {"employer", "employer.requisites", "requisites", "salary"})
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"employer", "employer.requisites", "requisites", "salary"})
    List<Employee> findAll(@Nullable Specification<Employee> spec, Sort sort);

    @EntityGraph(attributePaths = {"requisites"})
    @Query(value = "select e from Employee e where e.requisites.accountNumber = :accountNumber ")
    Optional<Employee> findByAccountNumber(@Param("accountNumber") String accountNumber);


    @EntityGraph(attributePaths = {"employer", "requisites", "salary", "transactions"})
    @Query(value = "select e from Employee e where e.id = :employeeId")
    Optional<Employee> findOneFull(@Param("employeeId") UUID employeeId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employee SET status = :status WHERE id = :id")
    void updateStatus(@Param("id") UUID id, @Param("status") String status);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employee SET status = :newStatus WHERE id IN ( \n" +
            "SELECT e.id FROM employee e \n" +
            "LEFT JOIN employer er on e.employer_id = er.id " +
            "WHERE er.id = :employerId AND e.status = :status\n" +
            ")")
    void updateStatusByEmployerIdAndStatus(
            @Param("employerId") UUID employerId,
            @Param("status") String status,
            @Param("newStatus") String newStatus);
}
