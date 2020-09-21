package com.test.task.service.commit;

import com.test.task.domain.respository.commit.Commit;
import com.test.task.service.commit.dto.CreateCommitDto;

import java.util.List;
import java.util.Optional;


public interface CommitService {

    Commit create(final CreateCommitDto dto);

    Commit getById(final Long id);

    List<Commit> findAllByRepository(final Long repositoryId);

    Optional<Commit> findBySha(final String sha);
}
