package ru.bogdanov.diplom.data.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.bogdanov.diplom.grpc.generated.ApproveStatus;
import ru.bogdanov.diplom.grpc.generated.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author SBogdanov
 * Сущность для хранения данных о транзакции
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Transaction extends AbstractEntity implements Serializable {

    /**
     * Cумма выплат транзакции
     */
    @Builder.Default
    @Min(value = 0, message = "Сумма выплат в транзакции не может быть меньше 0")
    private long totalSum = 0;

    /**
     * Статус транзакции
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.NEW_TRANSACTION;

    /**
     * дата выполнения транзакции
     */
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private LocalDateTime date = LocalDateTime.now();

    /**
     * Статус подтверждения
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus = ApproveStatus.TO_APPROVE;

    /**
     * Работник
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
