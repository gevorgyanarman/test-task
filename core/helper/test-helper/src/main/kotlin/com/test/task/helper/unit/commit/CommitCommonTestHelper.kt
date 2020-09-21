package com.test.task.helper.unit.commit

import com.test.task.domain.respository.Repository
import com.test.task.domain.respository.commit.Commit
import com.test.task.domain.user.User
import com.test.task.helper.AbstractTestHelper
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.commit.dto.CreateCommitDto
import java.time.LocalDateTime

open class CommitCommonTestHelper : AbstractTestHelper() {

    private val userCommonTestHelper = UserCommonTestHelper()
    private val repositoryCommonTestHelper = RepositoryCommonTestHelper()

    fun buildCreateCommitDto(userId: Long? = randomLong(),
                             repositoryId: Long? = randomLong(),
                             sha: String? = uuId(),
                             remoteCreationDate: LocalDateTime? = LocalDateTime.now()
    ): CreateCommitDto = CreateCommitDto(userId, repositoryId, sha, remoteCreationDate)

    fun buildCommit(user: User? = userCommonTestHelper.buildUser(),
                    repository: Repository? = repositoryCommonTestHelper.buildRepository(),
                    sha: String = uuId(),
                    remoteCreationDate: LocalDateTime = LocalDateTime.now()
    ): Commit = Commit(user, repository, sha, remoteCreationDate)
}