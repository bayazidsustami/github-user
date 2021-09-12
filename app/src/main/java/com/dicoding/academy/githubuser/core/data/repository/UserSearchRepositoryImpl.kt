package com.dicoding.academy.githubuser.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.UserPagingSource
import com.dicoding.academy.githubuser.core.data.dataSource.remote.networking.ApiService
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.domain.repository.UserSearchRepository
import com.dicoding.academy.githubuser.utility.Constants.COUNT_OF_PER_PAGE
import kotlinx.coroutines.flow.Flow

class UserSearchRepositoryImpl(
    private val apiService: ApiService
): UserSearchRepository {
    override fun searchUser(query: String): Flow<PagingData<UserItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = COUNT_OF_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {UserPagingSource(apiService, query)}
        ).flow
    }
}