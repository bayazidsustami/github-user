package com.dicoding.academy.githubuser.core.data.dataSource.remote

import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.utility.Result
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getDetailUser(username: String): Flow<Result<DetailUserResponse>>
}