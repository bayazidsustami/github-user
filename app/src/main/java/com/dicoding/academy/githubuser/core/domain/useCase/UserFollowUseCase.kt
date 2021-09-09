package com.dicoding.academy.githubuser.core.domain.useCase

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import kotlinx.coroutines.flow.Flow

interface UserFollowUseCase {
    fun getUserFollow(index: Int, username: String): Flow<PagingData<UserItem>>
}