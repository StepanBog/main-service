package ru.bogdanov.diplom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.diplom.data.exception.ServiceException;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.grpc.generated.error.ErrorCode;
import ru.bogdanov.diplom.grpc.generated.service.employer.SearchEmployerRequest;
import ru.bogdanov.diplom.repository.EmployerRepository;
import ru.bogdanov.diplom.repository.specification.entity.EmployerSpecification;
import ru.bogdanov.diplom.service.IEmployerService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author VKozlov
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmployerService implements IEmployerService {

    private final EmployerRepository employerRepository;

    @Override
    @Transactional
    public Employer save(@NotNull Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public Page<Employer> findAll(@NotNull SearchEmployerRequest searchRequest) {
        Specification<Employer> specification = EmployerSpecification.createSpecification(searchRequest);
        if (searchRequest.hasPageNumber()
                && searchRequest.hasPageSize()) {
            return employerRepository.findAll(
                    specification,
                    PageRequest.of(
                            searchRequest.getPageNumber(),
                            searchRequest.getPageSize()
                    ).withSort(Sort.Direction.DESC, "createdAt"));
        }

        List<Employer> findEmployers = employerRepository.findAll(
                specification,
                Sort.by(Sort.Direction.DESC, "createdAt"));
        return new PageImpl(findEmployers);
    }

    @Override
    public Employer findOne(@NotNull final UUID id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        String.format("Employer nof found id %s", id), ErrorCode.UNKNOWN_ERROR));
    }

    @Override
    public Employer findOneByInn(@NotNull String inn) {
        return employerRepository.findByInn(inn)
                .orElseThrow(() -> new ServiceException(
                        String.format("Employer nof found by inn %s", inn), ErrorCode.UNKNOWN_ERROR));
    }

    @Override
    public Employer findOneByInnFull(@NotNull String inn) {
        return employerRepository.findByInnFull(inn)
                .orElseThrow(() -> new ServiceException(
                        String.format("Employer nof found by inn %s", inn), ErrorCode.UNKNOWN_ERROR));
    }
}
