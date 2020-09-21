package com.test.task.repository.commit;

import com.test.task.domain.respository.commit.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {

    @Query("from Commit c left join fetch c.user where c.repository.id = :repositoryId")
    List<Commit> findAllByRepositoryId(@Param("repositoryId") final Long repositoryId);

    Optional<Commit> findBySha(final String sha);
}
