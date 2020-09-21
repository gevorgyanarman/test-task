package com.test.task.repository.user;

import com.test.task.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRemoteId(final String remoteId);

}
