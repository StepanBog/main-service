package ru.bogdanov.diplom.mapper;

import org.mapstruct.*;
import ru.bogdanov.diplom.data.model.Requisites;
import ru.bogdanov.diplom.mapper.common.BoolValueMapper;
import ru.bogdanov.diplom.mapper.common.StringValueMapper;
import ru.bogdanov.diplom.mapper.common.TimestampMapper;
import ru.bogdanov.diplom.mapper.common.UUIDValueMapper;

@Mapper(uses = {
        StringValueMapper.class,
        UUIDValueMapper.class,
        BoolValueMapper.class,
        TimestampMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequisitesMapper {

    Requisites transformToEntity(ru.bogdanov.diplom.grpc.generated.Requisites requisites);

    @Mapping(target = "id", ignore = true)
    Requisites update(@MappingTarget Requisites target, ru.bogdanov.diplom.grpc.generated.Requisites requisites);

    ru.bogdanov.diplom.grpc.generated.Requisites transform(Requisites requisites);
}
