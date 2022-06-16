package ru.bogdanov.diplom.mapper;

import org.mapstruct.*;
import ru.bogdanov.diplom.data.model.Employer;
import ru.bogdanov.diplom.mapper.common.BoolValueMapper;
import ru.bogdanov.diplom.mapper.common.StringValueMapper;
import ru.bogdanov.diplom.mapper.common.TimestampMapper;
import ru.bogdanov.diplom.mapper.common.UUIDValueMapper;

@Mapper(uses = {
        StringValueMapper.class,
        UUIDValueMapper.class,
        BoolValueMapper.class,
        TimestampMapper.class,
        RequisitesMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployerMapper {

    @Mapping(target = "requisites", ignore = true)
    ru.bogdanov.diplom.grpc.generated.Employer transform(Employer employer);

    ru.bogdanov.diplom.grpc.generated.Employer transformWithRequisites(Employer employer);

    @Mapping(target = "contactsList", source = "contacts")
    ru.bogdanov.diplom.grpc.generated.Employer transformFull(Employer employer);

    @Mapping(target = "contacts", source = "contactsList")
    Employer transform(ru.bogdanov.diplom.grpc.generated.Employer employer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requisites", ignore = true)
    Employer update(@MappingTarget Employer target, ru.bogdanov.diplom.grpc.generated.Employer employee);
}
