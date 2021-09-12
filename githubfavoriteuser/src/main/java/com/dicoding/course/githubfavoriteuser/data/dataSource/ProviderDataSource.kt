package com.dicoding.course.githubfavoriteuser.data.dataSource

import com.dicoding.course.githubfavoriteuser.data.UserModel

interface ProviderDataSource {
    fun getALlFavoriteUser(): List<UserModel>
}