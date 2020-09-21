package com.test.task.service.user;

import com.test.task.domain.user.User;
import com.test.task.service.user.dto.CreateOrUpdateUserDto;


public interface UserCompoundActionService {

    User createOrUpdate(final CreateOrUpdateUserDto dto);
}
