package ru.bogdanov.diplom.mapper;

import org.mapstruct.*;
import ru.bogdanov.diplom.data.model.Transaction;
import ru.bogdanov.diplom.mapper.common.BoolValueMapper;
import ru.bogdanov.diplom.mapper.common.StringValueMapper;
import ru.bogdanov.diplom.mapper.common.TimestampMapper;
import ru.bogdanov.diplom.mapper.common.UUIDValueMapper;

import java.util.List;

@Mapper(uses = {
        StringValueMapper.class,
        UUIDValueMapper.class,
        BoolValueMapper.class,
        TimestampMapper.class,
        EmployeeMapper.class
},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TransactionMapper {

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "approveStatus", source = "approveStatus", defaultValue = "TO_APPROVE")
    Transaction transformToEntity(ru.bogdanov.diplom.grpc.generated.Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalSum", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "approveStatus", ignore = true)
    @Mapping(target = "employee.requisites", ignore = true)
    @Mapping(target = "employee.employer", ignore = true)
    Transaction update(@MappingTarget Transaction target, ru.bogdanov.diplom.grpc.generated.Transaction source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalSum", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "approveStatus", ignore = true)
    @Mapping(target = "employee.requisites", ignore = true)
    @Mapping(target = "employee.employer", ignore = true)
    Transaction update(@MappingTarget Transaction target, Transaction source);

    @Mapping(target = "date", expression = "java(timestampMapper.mapToProto(transaction.getDate() == null ? transaction.getCreatedAt() : transaction.getDate()))")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employee.requisites", ignore = true)
    ru.bogdanov.diplom.grpc.generated.Transaction transform(Transaction transaction);

    @Mapping(target = "date", expression = "java(timestampMapper.mapToProto(transaction.getDate() == null ? transaction.getCreatedAt() : transaction.getDate()))")
    @Mapping(target = "employeeId", source = "transaction.employee.id")
    @Mapping(target = "employee.requisites", ignore = true)
    @Mapping(target = "employee.employer", ignore = true)
    @Mapping(target = "employee.requisites.accountNumber", source = "accountNumber")
    ru.bogdanov.diplom.grpc.generated.Transaction transform(Transaction transaction,
                                                               String accountNumber);

    List<ru.bogdanov.diplom.grpc.generated.Transaction> transform(List<Transaction> transactions);
}
