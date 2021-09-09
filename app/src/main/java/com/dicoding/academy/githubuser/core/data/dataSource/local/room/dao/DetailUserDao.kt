package com.dicoding.academy.githubuser.core.data.dataSource.local.room.dao

import android.database.Cursor
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DetailUserDao: BaseDao<DetailUserEntity>() {

    @Query("SELECT * FROM detail_user_entity WHERE login=:username")
    abstract fun getUser(username: String): Flow<DetailUserEntity>

    @Query("SELECT * FROM detail_user_entity")
    abstract fun getAllFavoriteUser(): DataSource.Factory<Int, DetailUserEntity>

    suspend fun saveUser(user: DetailUserEntity){
        insert(user)
    }

    @Query("SELECT EXISTS(SELECT * FROM detail_user_entity WHERE login=:username)")
    abstract fun getIfIsFavorite(username: String): Flow<Boolean>

    @Delete
    abstract suspend fun deleteUser(user: DetailUserEntity)

    @Query("SELECT * FROM detail_user_entity")
    abstract fun selectAll(): Cursor

    @Query("SELECT * FROM detail_user_entity WHERE login=:username")
    abstract fun selectByName(username: String): Cursor

    @Query("DELETE FROM detail_user_entity WHERE login=:username")
    abstract fun deleteByName(username: String): Cursor
}