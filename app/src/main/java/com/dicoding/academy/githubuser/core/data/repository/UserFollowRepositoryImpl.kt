package com.dicoding.academy.githubuser.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.data.dataSource.UserFollowPagingSource
import com.dicoding.academy.githubuser.core.data.dataSource.remote.networking.ApiService
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.domain.repository.UserFollowRepository
import com.dicoding.academy.githubuser.utility.Constants.COUNT_OF_PER_PAGE
import kotlinx.coroutines.flow.Flow

class UserFollowRepositoryImpl(
    private val apiService: ApiService
): UserFollowRepository {
    override fun getUserFollow(index: Int, username: String): Flow<PagingData<UserItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = COUNT_OF_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {UserFollowPagingSource(apiService, handlePath(index), username)}
        ).flow
    }

    private fun handlePath(index: Int): String{
        return when(index){
            0 -> "followers"
            1 -> "following"
            else -> throw IllegalArgumentException("unexpected code argument")
        }
    }
}