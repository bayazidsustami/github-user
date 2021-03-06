package com.dicoding.course.githubfavoriteuser.data

data class UserModel(
    val id: Int,
    val login: String,
    val company: String?,
    val publicRepos: Int?,
    val followers: Int?,
    val avatarUrl: String?,
    val following: Int?,
    val name: String,
    val location: String?,
    var isFavorite: Boolean
)