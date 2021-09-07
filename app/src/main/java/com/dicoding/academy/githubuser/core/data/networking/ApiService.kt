package com.dicoding.academy.githubuser.core.data.networking

import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserResponse
import com.dicoding.academy.githubuser.utility.Constants.COUNT_OF_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") countOfPage: Int = COUNT_OF_PER_PAGE
    ): UserResponse

    @GET("users/{username}")
    suspend fun detailUser(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/{follow}")
    suspend fun getListUserFollow(
        @Path("username") username: String,
        @Path("follow") follow: String,
        @Query("page") page: Int,
        @Query("per_page") countOfPage: Int = COUNT_OF_PER_PAGE
    ): List<UserItem>
}