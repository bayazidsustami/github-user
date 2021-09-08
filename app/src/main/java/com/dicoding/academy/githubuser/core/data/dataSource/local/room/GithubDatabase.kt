package com.dicoding.academy.githubuser.core.data.dataSource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.UserItemEntity
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.dao.DetailUserDao

@Database(entities = [UserItemEntity::class, DetailUserEntity::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase(){
    abstract fun detailUserDao(): DetailUserDao
}