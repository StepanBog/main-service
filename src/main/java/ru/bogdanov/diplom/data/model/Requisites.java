package ru.bogdanov.diplom.data.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author SBogdanov
 * Сущность для хранения реквизитов
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Requisites extends AbstractEntity implements Serializable {

    /**
     * Cнилс (без проблелов и разделителей)
     */
    private String snils;

    /**
     * Cерия паспора
     */
    private String passportSeries;

    /**
     * Номер паспорта
     */
    private String passportNumber;

    /**
     * Орган выдавший паспорт
     */
    private String passportIssued;

    /**
     * Код подразделения органа, выдавшего паспорт
     */
    private String passportIssuerCode;

    /**
     * Адрес регистрации работника
     */
    private String registrationAddress;

    /**
     * Дата выдачи паспорта работника
     */
    private LocalDate passportIssuedDate;

    /**
     * ИНН
     */
    private String inn;

    /**
     * КПП
     */
    private String kpp;

    /**
     * Номер счета в банке-получателе
     */
    private String accountNumber;

    /**
     * БИК банка-получателя
     */
    private String bik;

    /**
     * Имя работника
     */
    private String firstName;

    /**
     * Фамилия работника
     */
    private String lastName;

    /**
     * Отчество работника
     */
    private String patronymicName;

    /**
     * корр счет
     */
    private String corr;

    /**
     * Наименование банка
     */
    private String bankName;
}
