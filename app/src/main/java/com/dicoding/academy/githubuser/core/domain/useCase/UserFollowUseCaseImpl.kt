package com.dicoding.academy.githubuser.core.domain.useCase

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.domain.repository.UserFollowRepository
import kotlinx.coroutines.flow.Flow

class UserFollowUseCaseImpl constructor(
    private val repository: UserFollowRepository
): UserFollowUseCase {
    override fun getUserFollow(index: Int, username: String): Flow<PagingData<UserItem>> {
        return repository.getUserFollow(index, username)
    }
}