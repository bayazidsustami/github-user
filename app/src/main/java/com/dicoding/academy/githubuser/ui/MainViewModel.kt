package com.dicoding.academy.githubuser.ui

import com.dicoding.academy.githubuser.utility.JsonHelper
import com.dicoding.academy.githubuser.data.UsersItem

class MainViewModel(
    private val jsonHelper: JsonHelper
) {

    fun getListUser(): List<UsersItem> = jsonHelper.getUsers()
}