package com.dicoding.academy.githubuser.core.domain.repository

import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.utility.Result
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    suspend fun getUserDetail(username: String): Flow<Result<DetailUserUIModel>>
    fun getUser(username: String): Flow<DetailUserUIModel>
    suspend fun saveUser(user: DetailUserUIModel)
}