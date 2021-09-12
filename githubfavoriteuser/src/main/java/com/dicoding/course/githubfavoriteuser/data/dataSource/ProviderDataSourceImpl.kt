package com.dicoding.course.githubfavoriteuser.data.dataSource

import com.dicoding.course.githubfavoriteuser.data.UserModel
import com.dicoding.course.githubfavoriteuser.data.utils.ProviderHelper

class ProviderDataSourceImpl constructor(
    private val provider: ProviderHelper
): ProviderDataSource {
    override fun getALlFavoriteUser(): List<UserModel> {
        return provider.getAllFavoriteUser()
    }
}