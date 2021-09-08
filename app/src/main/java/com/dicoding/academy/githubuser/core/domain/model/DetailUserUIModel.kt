package com.dicoding.academy.githubuser.core.domain.model

data class DetailUserUIModel(
    val id: Int,
    val login: String,
    val company: String?,
    val publicRepos: Int?,
    val followers: Int?,
    val avatarUrl: String?,
    val following: Int?,
    val name: String,
    val location: String?,
)
