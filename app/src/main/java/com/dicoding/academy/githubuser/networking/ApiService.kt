package com.dicoding.academy.githubuser.networking

import com.dicoding.academy.githubuser.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.data.dataSource.remote.response.UserResponse
import com.dicoding.academy.githubuser.utility.Constants.COUNT_OF_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: String,
        @Query("per_page") countOfPage: String = COUNT_OF_PER_PAGE
    ): UserResponse

    @GET("users/{username}")
    suspend fun detailUser(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/{follow}")
    suspend fun getListUserFollow(
        @Path("username") username: String,
        @Path("follow") follow: String,
    ): UserResponse
}