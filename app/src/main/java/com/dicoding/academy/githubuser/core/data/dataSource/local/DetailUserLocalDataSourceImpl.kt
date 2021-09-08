package com.dicoding.academy.githubuser.core.data.dataSource.local

import android.util.Log
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.dao.DetailUserDao
import kotlinx.coroutines.flow.Flow

class DetailUserLocalDataSourceImpl(
    private val userDao: DetailUserDao
): LocalDataSource {
    override fun getUserDetail(username: String): Flow<DetailUserEntity> {
        Log.d("DATAS", "LOCAL")
        return userDao.getUser(username)
    }

    override suspend fun saveUser(user: DetailUserEntity) {
        if (user.isFavorite){
            userDao.saveUser(user)
        }else{
            userDao.deleteUser(user)
        }
    }

    override fun getUserIfExists(username: String): Flow<Boolean> {
        return userDao.getIfIsFavorite(username)
    }
}