package com.dicoding.academy.githubuser.core.data.dataSource.local

import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getUserDetail(username: String): Flow<DetailUserEntity>
    suspend fun saveUser(user: DetailUserEntity)
    fun getUserIfExists(username: String): Flow<Boolean>
}