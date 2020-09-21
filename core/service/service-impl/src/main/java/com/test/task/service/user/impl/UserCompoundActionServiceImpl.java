package com.test.task.service.user.impl;

import com.test.task.domain.user.User;
import com.test.task.service.user.UserCompoundActionService;
import com.test.task.service.user.UserService;
import com.test.task.service.user.dto.CreateOrUpdateUserDto;
import com.test.task.service.user.dto.CreateUserDto;
import com.test.task.service.user.dto.UpdateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;


@Service
class UserCompoundActionServiceImpl implements UserCompoundActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCompoundActionServiceImpl.class);

    private final UserService userService;

    UserCompoundActionServiceImpl(final UserService userService) {
        this.userService = userService;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public User createOrUpdate(final CreateOrUpdateUserDto dto) {
        LOGGER.debug("Processing user create or update for the provided dto - {}", dto);
        Assert.notNull(dto, "The user create or update dto should not be null");
        final Optional<User> oldUser = userService.findByRemoteId(dto.getRemoteId());
        final User user = oldUser.isPresent() ?
                userService.update(new UpdateUserDto(
                        oldUser.get().getId(),
                        dto.getLogin())
                ) : userService.create(new CreateUserDto(dto.getRemoteId(), dto.getLogin()));
        LOGGER.debug(
                "Successfully processed user create or update for the provided dto - {}, result - {}",
                dto,
                user
        );
        return user;
    }
}
