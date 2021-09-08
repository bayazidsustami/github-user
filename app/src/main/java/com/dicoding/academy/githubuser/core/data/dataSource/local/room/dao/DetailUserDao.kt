package com.dicoding.academy.githubuser.core.data.dataSource.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DetailUserDao: BaseDao<DetailUserEntity>() {

    @Query("SELECT * FROM detail_user_entity WHERE login=:username")
    abstract fun getUser(username: String): Flow<DetailUserEntity>

    suspend fun saveUser(user: DetailUserEntity){
        insert(user)
    }
}