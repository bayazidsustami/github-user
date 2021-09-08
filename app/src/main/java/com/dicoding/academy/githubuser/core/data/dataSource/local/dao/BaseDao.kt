package com.dicoding.academy.githubuser.core.data.dataSource.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy


abstract class BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(list: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(data: T)
}