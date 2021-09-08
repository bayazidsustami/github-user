package com.dicoding.academy.githubuser.core.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.UserItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao: BaseDao<UserItemEntity>(){

    @Query("SELECT * FROM user_item_entity")
    abstract fun getUsers(query: String): Flow<List<UserItemEntity>>

    suspend fun saveAll(users: List<UserItemEntity>){
        insert(users)
    }
}