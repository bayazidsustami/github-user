package com.dicoding.academy.githubuser.core.data.dataSource.local.entity

import com.squareup.moshi.Json

data class DetailUserEntity(
    val gistsUrl: String,
    val reposUrl: String,
    val followingUrl: String,
    val twitterUsername: String,
    val bio: String,
    val createdAt: String,
    val login: String,
    val type: String,
    val blog: String,
    val subscriptionsUrl: String,
    val updatedAt: String,
    val siteAdmin: Boolean,
    val company: String,
    val id: Int,
    val publicRepos: Int,
    val gravatarId: String,
    val email: String,
    val organizationsUrl: String,
    val hireable: Boolean,
    val starredUrl: String,
    val followersUrl: String,
    val publicGists: Int,
    val url: String,
    val receivedEventsUrl: String,
    val followers: Int,
    val avatarUrl: String,
    val eventsUrl: String,
    val htmlUrl: String,
    val following: Int,
    val name: String,
    val location: String,
    val nodeId: String
)