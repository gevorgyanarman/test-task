package com.test.task.domain.respository.commit;

import com.test.task.domain.respository.Repository;
import com.test.task.domain.user.User;
import com.test.task.repository.domain.AbstractUuidAwareDomainEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commit",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_commit_uuid", columnNames = {"uuid"}),
                @UniqueConstraint(name = "uk_commit_sha", columnNames = {"sha"})},
        indexes = {
                @Index(name = "idx_commit_repository_id", columnList = "repository_id"),
                @Index(name = "idx_commit_sha", columnList = "sha")
        }
)
public class Commit extends AbstractUuidAwareDomainEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_commit_user_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repository_id", foreignKey = @ForeignKey(name = "fk_commit_repository_id"))
    private Repository repository;

    @Column(name = "sha", nullable = false, unique = true)
    private String sha;

    @Column(name = "remote_created", nullable = false)
    private LocalDateTime remoteCreationDate;

    Commit() {
        super();
    }

    public Commit(final User user, final Repository repository, final String sha, final LocalDateTime remoteCreationDate) {
        this.user = user;
        this.repository = repository;
        this.sha = sha;
        this.remoteCreationDate = remoteCreationDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commit)) {
            return false;
        }
        final Commit commit = (Commit) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getIdOrNull(user), getIdOrNull(commit.user))
                .append(getIdOrNull(repository), getIdOrNull(commit.repository))
                .append(sha, commit.sha)
                .append(remoteCreationDate, commit.remoteCreationDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getIdOrNull(user))
                .append(getIdOrNull(repository))
                .append(sha)
                .append(remoteCreationDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", getIdOrNull(user))
                .append("repository", getIdOrNull(repository))
                .append("sha", sha)
                .append("remoteCreationDate", remoteCreationDate)
                .toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(final Repository repository) {
        this.repository = repository;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(final String sha) {
        this.sha = sha;
    }

    public LocalDateTime getRemoteCreationDate() {
        return remoteCreationDate;
    }

    public void setRemoteCreationDate(final LocalDateTime remoteCreationDate) {
        this.remoteCreationDate = remoteCreationDate;
    }
}
