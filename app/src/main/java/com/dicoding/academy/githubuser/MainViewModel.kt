package com.dicoding.academy.githubuser

class MainViewModel(
    private val jsonHelper: JsonHelper
) {

    fun getListUser(): List<UsersItem> = jsonHelper.getUsers()
}