package ru.bogdanov.diplom.service;

import org.springframework.data.domain.Page;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.grpc.generated.service.employer.SearchEmployerRequest;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author SBogdanov
 * Сервис для работы с работодателем
 */
public interface IEmployerService {

    /**
     * Сохранение сущности работодателя
     */
    Employer save(@NotNull final Employer employee);

    /**
     * Поиск работодатель по критериям поиска
     *
     * @param searchRequest - сущность с параметрами поиска работодателей
     * @return найденные работодатели, либо пустой список
     */
    Page<Employer> findAll(@NotNull final SearchEmployerRequest searchRequest);

    /**
     * Поиск работодателя
     *
     * @param employerId - id работодателя
     * @return найденный работодатель
     */
    Employer findOne(@NotNull final UUID employerId);

    /**
     * Поиск работодателя по inn
     *
     * @param employerInn - инн работодателя
     * @return найденный работодатель
     */
    Employer findOneByInn(@NotNull final String employerInn);

    /**
     * Поиск работодателя по inn  со всеми вложенными сущностями
     *
     * @param employerInn - инн работодателя
     * @return найденный работодатель
     */
    Employer findOneByInnFull(@NotNull final String employerInn);
}
