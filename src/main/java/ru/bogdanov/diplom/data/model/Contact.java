package ru.bogdanov.diplom.data.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.bogdanov.diplom.grpc.generated.ContactsPosition;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Сущность для хранения данных о контактах работодателя
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Contact extends AbstractEntity implements Serializable {

    /**
     * Работодатель
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    /**
     * Должность контактного лица
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ContactsPosition position = ContactsPosition.EMPLOYERS_CONTACT;

    /**
     * Имя контактного лица
     */
    private String name;

    /**
     * Телефон контактного лица
     */
    private String phone;

    /**
     * Почта
     */
    private String email;
}
