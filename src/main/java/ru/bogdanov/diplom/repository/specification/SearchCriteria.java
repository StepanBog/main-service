package ru.bogdanov.diplom.repository.specification;

import lombok.Data;
import org.hibernate.query.criteria.internal.path.AbstractFromImpl;
import ru.bogdanov.diplom.data.enums.SearchOperation;

import javax.persistence.criteria.JoinType;

@Data
public class SearchCriteria {

    private final String key;
    private final SearchOperation operation;
    private final Object value;
    private final JoinType joinType;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this(key, operation, value, AbstractFromImpl.DEFAULT_JOIN_TYPE);
    }

    public SearchCriteria(String key, SearchOperation operation, Object value, JoinType joinType) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.joinType = joinType;
    }
}
