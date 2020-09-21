package com.test.task.service.user.impl;

import com.test.task.domain.user.User;
import com.test.task.repository.user.UserRepository;
import com.test.task.service.commons.EntityNotFoundForIdException;
import com.test.task.service.user.UserService;
import com.test.task.service.user.dto.CreateUserDto;
import com.test.task.service.user.dto.UpdateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public User create(final CreateUserDto dto) {
        LOGGER.debug("Creating user for the provided dto - {}", dto);
        Assert.notNull(dto, "The user creation dto should not be null");
        final User user = userRepository.save(
                new User(dto.getLogin(), dto.getRemoteId())
        );
        LOGGER.debug("Successfully created user for the provided dto - {}", dto);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByRemoteId(final String remoteId) {
        LOGGER.debug("Finding user for the provided remote id - {}", remoteId);
        Assert.hasText(remoteId, "The remote id should not be null or empty");
        final Optional<User> userOptional = userRepository.findByRemoteId(remoteId);
        LOGGER.debug(
                "Successfully processed user lookup for the provided remote id - {}, result - {}",
                remoteId,
                userOptional
        );
        return userOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(final Long id) {
        LOGGER.debug("Retrieving user for the provided id - {}", id);
        Assert.notNull(id, "The user id should not be null");
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundForIdException(id, User.class));
        LOGGER.debug("Successfully retrieved user for the provided id - {}, result - {}", id, user);
        return user;
    }

    @Transactional
    @Override
    public User update(final UpdateUserDto dto) {
        LOGGER.debug("Updating user for the provided dto - {}", dto);
        Assert.notNull(dto, "The user update dto should not be null");
        final User user = getById(dto.getId());
        user.setLogin(dto.getLogin());
        final User updatedUser = userRepository.save(user);
        LOGGER.debug("Successfully updated user for the provided dto - {}, result - {}", dto, user);
        return updatedUser;
    }
}
