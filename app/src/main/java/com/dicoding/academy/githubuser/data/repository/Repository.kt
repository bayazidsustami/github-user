package com.dicoding.academy.githubuser.data.repository

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.data.dataSource.remote.response.UserItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    interface UserSearch{
        fun getUserSearch(query: String): Flow<PagingData<UserItem>>
    }

    interface UserDetail{
        suspend fun getUserDetail(username: String): Flow<DetailUserResponse>
    }
}