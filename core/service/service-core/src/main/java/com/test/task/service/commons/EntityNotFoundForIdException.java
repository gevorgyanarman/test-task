package com.test.task.service.commons;

import com.test.task.repository.domain.AbstractDomainEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class EntityNotFoundForIdException extends RuntimeException {

    private final Long id;
    private final Class<? extends AbstractDomainEntity> entityClass;

    public EntityNotFoundForIdException(final Long id, final Class<? extends AbstractDomainEntity> entityClass) {
        super(String.format("No entity for id - %d for class - %s was found", id, entityClass));
        this.id = id;
        this.entityClass = entityClass;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("entityClass", entityClass)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public Class<? extends AbstractDomainEntity> getEntityClass() {
        return entityClass;
    }
}
