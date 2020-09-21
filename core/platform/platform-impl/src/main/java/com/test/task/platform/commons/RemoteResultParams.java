package com.test.task.platform.commons;

import java.util.Optional;


public interface RemoteResultParams<T> {

    Optional<T> result();

    boolean notFetched();

    static <T> RemoteResultParams<T> ofError() {
        return RemoteErrorResultParams.getInstance();
    }

    static <T> RemoteResultParams<T> ofSuccess(final T result) {
        return new RemoteSuccessResultParams<>(result);
    }
}
