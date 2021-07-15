package com.dicoding.academy.githubuser.data.dataSource.remote

import com.dicoding.academy.githubuser.data.dataSource.remote.response.DetailUserResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getDetailUser(username: String): Flow<DetailUserResponse>
}