package com.dicoding.academy.githubuser.core.domain.repository

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import kotlinx.coroutines.flow.Flow

interface UserSearchRepository {
    fun searchUser(query: String): Flow<PagingData<UserItem>>
}