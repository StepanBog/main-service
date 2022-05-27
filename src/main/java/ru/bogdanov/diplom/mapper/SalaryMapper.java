package ru.bogdanov.diplom.mapper;

import org.mapstruct.*;
import ru.bogdanov.diplom.data.model.Salary;
import ru.bogdanov.diplom.mapper.common.TimestampMapper;
import ru.bogdanov.diplom.mapper.common.UUIDValueMapper;

@Mapper(uses = {TimestampMapper.class, UUIDValueMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SalaryMapper {

    @Mapping(target = "availableCash", source = "availableCash", defaultValue = "0L")
    @Mapping(target = "earnedForMonth", source = "earnedForMonth", defaultValue = "0L")
    @Mapping(target = "date",source = "period")
    Salary transformToEntity(ru.bogdanov.diplom.grpc.generated.Salary salary);

    @Mapping(target = "availableCash", source = "availableCash", defaultValue = "0L")
    @Mapping(target = "earnedForMonth", source = "earnedForMonth", defaultValue = "0L")
    @Mapping(target = "period",source = "date")

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ru.bogdanov.diplom.grpc.generated.Salary transform(Salary salary);

    @Named("transformFullSalary")
    @Mapping(target = "earnedForMonth", source = "earnedForMonth", defaultValue = "0L")
    @Mapping(target = "availableCash", source = "availableCash", defaultValue = "0L")
    @Mapping(target = "period",source = "date")
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ru.bogdanov.diplom.grpc.generated.Salary transformFull(Salary salary);

    @Mapping(target = "id", ignore = true)
    Salary update(@MappingTarget Salary target, ru.bogdanov.diplom.grpc.generated.Salary salary);
}
