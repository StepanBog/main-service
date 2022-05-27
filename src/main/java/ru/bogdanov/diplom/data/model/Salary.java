package ru.bogdanov.diplom.data.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author SBogdanov
 * Сущность для хранения данных о счете работника
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Salary extends AbstractEntity implements Serializable {

    /**
     * Средства доступные для вывода.
     * Сумма в копейках.
     */
    @Builder.Default
    private long availableCash = 0;

    /**
     * Средства, заработанный за месяц без учета досрочных вычетов.
     */
    @Builder.Default
    private long earnedForMonth = 0;

    /**
     * Ставка
     */
    @Builder.Default
    private long rate = 0;

    /**
     * Дата трудоустройства
     */
    private LocalDate date;

    /**
     * дата обновления информации
     */
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime salaryUpdateAt;
}
