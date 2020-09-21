package com.test.task.helper.interation.commit

import com.test.task.domain.respository.commit.Commit
import com.test.task.helper.interation.repository.RepositoryIntegrationTestHelper
import com.test.task.helper.interation.user.UserIntegrationTestHelper
import com.test.task.helper.unit.commit.CommitCommonTestHelper
import com.test.task.service.commit.CommitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class CommitIntegrationTestHelper @Autowired constructor(
        private val commitService: CommitService,
        private val userIntegrationTestHelper: UserIntegrationTestHelper,
        private val repositoryIntegrationTestHelper: RepositoryIntegrationTestHelper
) : CommitCommonTestHelper() {

    fun persistCommit(userId: Long = userIntegrationTestHelper.persistUser().id,
                      repositoryId: Long = repositoryIntegrationTestHelper.persistRepository().id,
                      sha: String? = uuId(),
                      remoteCreationDate: LocalDateTime? = LocalDateTime.now()
    ): Commit = buildCreateCommitDto(
            userId = userId,
            repositoryId = repositoryId,
            sha = sha,
            remoteCreationDate = remoteCreationDate
    ).let { commitService.create(it) }
}