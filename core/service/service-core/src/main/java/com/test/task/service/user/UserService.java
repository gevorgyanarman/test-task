package com.test.task.service.user;

import com.test.task.domain.user.User;
import com.test.task.service.user.dto.CreateUserDto;
import com.test.task.service.user.dto.UpdateUserDto;

import java.util.Optional;


public interface UserService {

    User create(final CreateUserDto dto);

    Optional<User> findByRemoteId(final String remoteId);

    User getById(final Long id);

    User update(final UpdateUserDto dto);
}
