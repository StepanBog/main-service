package ru.bogdanov.diplom.data.model;

import lombok.*;
import ru.bogdanov.diplom.grpc.generated.EmployeeStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author SBogdanov
 * Сущность для хранения данных о работнике
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Employee extends AbstractEntity implements Serializable {

    /**
     * Телефон работника
     */
    private String phone;

    /**
     * Статус пользователя
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.NEW_EMPLOYEE;

    /**
     * Работодатель
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    /**
     * Реквизиты работника
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "requisites_id")
    private Requisites requisites;

    /**
     * Интервалы дней приостановки обслуживания
     */
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "employee")
    @OrderBy("createdAt DESC")
    private Set<Transaction> transactions = new HashSet<>();


    /**
     * Счёт работника
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "salary_id")
    private Salary salary;

}
