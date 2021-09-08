package com.dicoding.academy.githubuser.core.data.dataSource.local

import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.dao.DetailUserDao
import kotlinx.coroutines.flow.Flow

class DetailUserLocalDataSourceImpl(
    private val userDao: DetailUserDao
): LocalDataSource {
    override fun getUserDetail(username: String): Flow<DetailUserEntity> {
        return userDao.getUser(username)
    }

    override suspend fun saveUser(user: DetailUserEntity) {
        return userDao.saveUser(user)
    }
}