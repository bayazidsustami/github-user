package com.dicoding.academy.githubuser.data.repository

import com.dicoding.academy.githubuser.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.data.dataSource.remote.response.DetailUserResponse
import kotlinx.coroutines.flow.Flow

class UserDetailRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): Repository.UserDetail {
    override suspend fun getUserDetail(username: String): Flow<DetailUserResponse> {
        return remoteDataSource.getDetailUser(username)
    }
}