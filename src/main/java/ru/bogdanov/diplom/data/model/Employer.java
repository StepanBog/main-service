package ru.bogdanov.diplom.data.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author SBogdanov
 * Сущность для хранения данных о работодателе
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Employer extends AbstractEntity implements Serializable {

    /**
     * Название работодателя
     */
    private String name;

    /**
     * Email работодателя
     */
    private String email;

    /**
     * Реквизиты работодателя
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "requisites_id")
    private Requisites requisites;

}
