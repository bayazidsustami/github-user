package com.dicoding.academy.githubuser.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dicoding.academy.githubuser.data.dataSource.UserPagingSource
import com.dicoding.academy.githubuser.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.networking.ApiService
import com.dicoding.academy.githubuser.utility.Constants.COUNT_OF_PER_PAGE
import kotlinx.coroutines.flow.Flow

class UserSearchRepository(
    private val apiService: ApiService
): Repository.UserSearch {
    override fun getUserSearch(query: String): Flow<PagingData<UserItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = COUNT_OF_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {UserPagingSource(apiService, query)}
        ).flow
    }
}