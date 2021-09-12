package com.dicoding.course.githubfavoriteuser.data.repository

import com.dicoding.course.githubfavoriteuser.data.UserModel
import com.dicoding.course.githubfavoriteuser.data.dataSource.ProviderDataSource

class UserRepositoryImpl constructor(
    private val dataSource: ProviderDataSource
): UserRepository {
    override fun getAllFavoriteUser(): List<UserModel> {
        return dataSource.getALlFavoriteUser()
    }
}