package com.dicoding.academy.githubuser.core.data.dataSource.local

import androidx.paging.DataSource
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getUserDetail(username: String): Flow<DetailUserEntity>
    fun getAllFavoriteUser(): DataSource.Factory<Int, DetailUserEntity>
    suspend fun saveUser(user: DetailUserEntity)
    fun getUserIfExists(username: String): Flow<Boolean>
}