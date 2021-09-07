package com.dicoding.academy.githubuser.core.data.dataSource.local.room

import androidx.room.RoomDatabase
import com.dicoding.academy.githubuser.core.data.dataSource.local.dao.UserDao

abstract class GithubDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}