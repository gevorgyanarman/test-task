package com.test.task.repository.repository;

import com.test.task.domain.respository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RepositoryRepository extends JpaRepository<Repository, Long> {

    Optional<Repository> findByRemoteId(final String remoteId);

    Optional<Repository> findByUuId(final String uuId);

    @Query(value = "from Repository r where r.fullName like %:text% order by r.created desc")
    Page<Repository> search(@Param("text") final String query, final Pageable pageable);
}
