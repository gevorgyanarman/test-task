package com.test.task.repository.repository.contributor;

import com.test.task.domain.respository.RepositoryContributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RepositoryContributorRepository extends JpaRepository<RepositoryContributor, Long> {

    Optional<RepositoryContributor> findByRepositoryIdAndUserId(final long repositoryId, final Long userId);

    @Query("from RepositoryContributor rc left join fetch rc.user where rc.repository.id = :repositoryId")
    List<RepositoryContributor> findByRepositoryId(@Param("repositoryId")final long repositoryId);
}
