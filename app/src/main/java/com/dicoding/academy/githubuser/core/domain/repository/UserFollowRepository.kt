package com.dicoding.academy.githubuser.core.domain.repository

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import kotlinx.coroutines.flow.Flow

interface UserFollowRepository{
    fun getUserFollow(index: Int, username: String): Flow<PagingData<UserItem>>
}