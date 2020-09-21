package com.test.task.repository.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractUuidAwareDomainEntity extends AbstractDomainEntity {

    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private String uuId = UUID.randomUUID().toString();

    public AbstractUuidAwareDomainEntity() {
    }

    public AbstractUuidAwareDomainEntity(final String uuId) {
        this.uuId = uuId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(final String uuId) {
        this.uuId = uuId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUuidAwareDomainEntity)) {
            return false;
        }
        final AbstractUuidAwareDomainEntity that = (AbstractUuidAwareDomainEntity) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(uuId, that.uuId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uuId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuId", uuId)
                .toString();
    }
}
