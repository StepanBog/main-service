package ru.bogdanov.diplom.mapper;

import org.mapstruct.*;
import ru.bogdanov.diplom.data.model.Employee;
import ru.bogdanov.diplom.mapper.common.BoolValueMapper;
import ru.bogdanov.diplom.mapper.common.StringValueMapper;
import ru.bogdanov.diplom.mapper.common.TimestampMapper;
import ru.bogdanov.diplom.mapper.common.UUIDValueMapper;

@Mapper(uses = {
        StringValueMapper.class,
        UUIDValueMapper.class,
        BoolValueMapper.class,
        TimestampMapper.class,
        SalaryMapper.class,
        TransactionMapper.class,
        EmployerMapper.class,
        RequisitesMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "employer.id", source = "employer.id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Employee transformToEntity(ru.bogdanov.diplom.grpc.generated.Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employer", ignore = true)
    @Mapping(target = "salary", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    Employee update(@MappingTarget Employee target, Employee employee);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "employer", ignore = true)
    @Mapping(target = "employer.id", source = "employer.id")
    @Mapping(target = "employer.name", source = "employer.name")
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ru.bogdanov.diplom.grpc.generated.Employee transformFull(Employee employee);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "employer", ignore = true)
    @Mapping(target = "employer.id", source = "employer.id")
    @Mapping(target = "employer.name", source = "employer.name")
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ru.bogdanov.diplom.grpc.generated.Employee transform(Employee employee);

}
