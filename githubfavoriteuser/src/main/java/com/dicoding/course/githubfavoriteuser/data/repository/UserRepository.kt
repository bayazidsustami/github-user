package com.dicoding.course.githubfavoriteuser.data.repository

import com.dicoding.course.githubfavoriteuser.data.UserModel

interface UserRepository {
    fun getAllFavoriteUser(): List<UserModel>
}