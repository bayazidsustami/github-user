package com.dicoding.academy.githubuser.core.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_user_entity")
data class DetailUserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="login") val login: String,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "public_repos") val publicRepos: Int?,
    @ColumnInfo(name = "followers") val followers: Int?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "following") val following: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)