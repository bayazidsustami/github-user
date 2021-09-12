package com.dicoding.course.githubfavoriteuser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.course.githubfavoriteuser.data.UserModel
import com.dicoding.course.githubfavoriteuser.data.repository.UserRepository

class MainViewModel (
    private val repository: UserRepository
): ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> get() = _users


    fun getUser(){
        _users.postValue(repository.getAllFavoriteUser())
    }
}