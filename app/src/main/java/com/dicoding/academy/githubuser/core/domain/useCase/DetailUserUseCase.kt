package com.dicoding.academy.githubuser.core.domain.useCase

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.common.Result
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import kotlinx.coroutines.flow.Flow

interface DetailUserUseCase {
    suspend fun getUserDetail(username: String): Flow<Result<DetailUserUIModel>>
    fun getUser(username: String): Flow<DetailUserUIModel>
    suspend fun saveUser(user: DetailUserUIModel)
    fun getUserIfExists(username: String): Flow<Boolean>
    fun getAllUser(): Flow<PagingData<DetailUserUIModel>>
}