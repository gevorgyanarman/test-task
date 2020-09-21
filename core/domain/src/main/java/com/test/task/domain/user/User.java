package com.test.task.domain.user;

import com.test.task.repository.domain.AbstractUuidAwareDomainEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "user_", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_uuid", columnNames = {"uuid"}),
        @UniqueConstraint(name = "uk_user_remote_id", columnNames = {"remote_id"})},
        indexes = {
                @Index(name = "idx_user_remote_id", columnList = "remote_id")
        }
)
public class User extends AbstractUuidAwareDomainEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "remote_id", nullable = false, unique = true)
    private String remoteId;

    User() {
        super();
    }

    public User(final String login, final String remoteId) {
        this.login = login;
        this.remoteId = remoteId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        final User user = (User) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(login, user.login)
                .append(remoteId, user.remoteId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(login)
                .append(remoteId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("login", login)
                .append("remoteId", remoteId)
                .toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(final String remoteId) {
        this.remoteId = remoteId;
    }
}
