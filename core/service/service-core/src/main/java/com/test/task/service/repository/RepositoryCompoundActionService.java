package com.test.task.service.repository;

import com.test.task.domain.respository.Repository;
import com.test.task.service.repository.dto.CreateOrUpdateRepositoryDto;

public interface RepositoryCompoundActionService {

    Repository createOrUpdate(final CreateOrUpdateRepositoryDto dto);
}
