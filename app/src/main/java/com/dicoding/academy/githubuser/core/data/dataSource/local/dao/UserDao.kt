package com.dicoding.academy.githubuser.core.data.dataSource.local.dao

import androidx.room.Dao
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.UserItemEntity

@Dao
abstract class UserDao: BaseDao<UserItemEntity>(){

    suspend fun saveAll(users: List<UserItemEntity>){
        insert(users)
    }
}