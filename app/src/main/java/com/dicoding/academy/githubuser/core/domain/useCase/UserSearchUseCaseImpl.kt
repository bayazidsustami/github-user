package com.dicoding.academy.githubuser.core.domain.useCase

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.domain.repository.UserSearchRepository
import kotlinx.coroutines.flow.Flow

class UserSearchUseCaseImpl constructor(
    private val repository: UserSearchRepository
): UserSearchUseCase {
    override fun searchUser(query: String): Flow<PagingData<UserItem>> {
        return repository.searchUser(query)
    }
}