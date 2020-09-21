package com.test.task.domain.respository;

import com.test.task.repository.domain.AbstractUuidAwareDomainEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "repository",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_repository_uuid", columnNames = {"uuid"}),
                @UniqueConstraint(name = "uk_repository_remote_id", columnNames = {"remote_id"})},
        indexes = {
                @Index(name = "idx_repository_remote_id", columnList = "remote_id"),
                @Index(name = "idx_repository_full_name", columnList = "full_name")
        }
)
public class Repository extends AbstractUuidAwareDomainEntity {

    @Column(name = "remote_id", nullable = false, unique = true)
    private String remoteId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    Repository() {
        super();
    }

    public Repository(final String remoteId,
                      final String name,
                      final String fullName) {
        this.remoteId = remoteId;
        this.name = name;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Repository)) {
            return false;
        }
        final Repository that = (Repository) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(remoteId, that.remoteId)
                .append(name, that.name)
                .append(fullName, that.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(remoteId)
                .append(name)
                .append(fullName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("remoteId", remoteId)
                .append("name", name)
                .append("fullName", fullName)
                .toString();
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(final String remoteId) {
        this.remoteId = remoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
