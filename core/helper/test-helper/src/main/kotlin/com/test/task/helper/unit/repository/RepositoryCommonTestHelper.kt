package com.test.task.helper.unit.repository

import com.test.task.domain.respository.Repository
import com.test.task.helper.AbstractTestHelper
import com.test.task.service.repository.dto.CreateOrUpdateRepositoryDto
import com.test.task.service.repository.dto.CreateRepositoryDto
import com.test.task.service.repository.dto.SearchRepositoryDto
import com.test.task.service.repository.dto.UpdateRepositoryDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

open class RepositoryCommonTestHelper : AbstractTestHelper() {

    fun buildCreateRepositoryDto(remoteId: String? = uuId(),
                                 name: String? = uuId(),
                                 fullName: String? = uuId()
    ): CreateRepositoryDto = CreateRepositoryDto(remoteId, name, fullName)

    fun buildUpdateRepositoryDto(id: Long? = randomLong(),
                                 name: String? = uuId(),
                                 fullName: String? = uuId()
    ): UpdateRepositoryDto = UpdateRepositoryDto(id, name, fullName)

    fun buildSearchRepositoryDto(page: Int = 0,
                                 size: Int = 100,
                                 query: String? = uuId()
    ): SearchRepositoryDto = SearchRepositoryDto(page, size, query)

    fun buildCreateOrUpdateRepositoryDto(remoteId: String? = uuId(),
                                         name: String? = uuId(),
                                         fullName: String? = uuId()
    ): CreateOrUpdateRepositoryDto = CreateOrUpdateRepositoryDto(remoteId, name, fullName)

    fun buildRepository(remoteId: String? = uuId(),
                        name: String? = uuId(),
                        fullName: String? = uuId()
    ): Repository = Repository(remoteId, name, fullName)

    fun buildPage(repositories: List<Repository> = listOf(buildRepository(), buildRepository()),
                  elementsCount: Long = 100): Page<Repository>  {
        return PageImpl(repositories, Pageable.unpaged(), elementsCount)
    }
}