package com.test.task.platform.commons;

import java.util.Optional;


final class RemoteErrorResultParams<T> implements RemoteResultParams<T> {

    static final RemoteErrorResultParams<?> REMOTE_ERROR_RESULT_PARAMS = new RemoteErrorResultParams<>();

    static <T> RemoteErrorResultParams<T> getInstance() {
        return (RemoteErrorResultParams<T>) REMOTE_ERROR_RESULT_PARAMS;
    }

    private RemoteErrorResultParams() {
        super();
    }

    @Override
    public Optional<T> result() {
        return Optional.empty();
    }

    @Override
    public boolean notFetched() {
        return true;
    }
}
