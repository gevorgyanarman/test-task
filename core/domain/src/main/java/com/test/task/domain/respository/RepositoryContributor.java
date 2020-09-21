package com.test.task.domain.respository;

import com.test.task.domain.user.User;
import com.test.task.repository.domain.AbstractUuidAwareDomainEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "repository_contributor", uniqueConstraints = {
        @UniqueConstraint(name = "uk_repository_contributor_uuid", columnNames = {"uuid"}),
        @UniqueConstraint(name = "uk_repository_contributor_id_repository_id", columnNames = {"repository_id", "user_id"})},
        indexes = {
                @Index(name = "idx_repository_contributor_repository_id", columnList = "repository_id"),
                @Index(name = "idx_repository_contributor_repository_id_user_id", columnList = "repository_id, user_id")
        }
)
public class RepositoryContributor extends AbstractUuidAwareDomainEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repository_id", foreignKey = @ForeignKey(name = "fk_repository_contributor_repository_id"))
    private Repository repository;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_repository_contributor_user_id"))
    private User user;

    RepositoryContributor() {
        super();
    }

    public RepositoryContributor(final Repository repository, final User user) {
        this.repository = repository;
        this.user = user;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RepositoryContributor)) {
            return false;
        }
        final RepositoryContributor that = (RepositoryContributor) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getIdOrNull(repository), getIdOrNull(that.repository))
                .append(getIdOrNull(user), getIdOrNull(that.user))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getIdOrNull(repository))
                .append(getIdOrNull(user))
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("repository", getIdOrNull(repository))
                .append("user", getIdOrNull(user))
                .toString();
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(final Repository repository) {
        this.repository = repository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}