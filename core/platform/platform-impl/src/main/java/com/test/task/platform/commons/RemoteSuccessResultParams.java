package com.test.task.platform.commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;


final class RemoteSuccessResultParams<T> implements RemoteResultParams<T> {

    private final T result;

    RemoteSuccessResultParams(final T result) {
        this.result = result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RemoteSuccessResultParams)) {
            return false;
        }
        final RemoteSuccessResultParams<?> that = (RemoteSuccessResultParams<?>) o;
        return new EqualsBuilder()
                .append(result, that.result)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(result)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("result", result)
                .toString();
    }

    @Override
    public Optional<T> result() {
        return Optional.of(result);
    }

    @Override
    public boolean notFetched() {
        return false;
    }
}
