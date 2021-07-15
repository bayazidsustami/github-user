package com.dicoding.academy.githubuser.ui.detail

import androidx.lifecycle.*
import com.dicoding.academy.githubuser.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.data.repository.Repository
import com.dicoding.academy.githubuser.utility.Result

class DetailUserViewModel(
    private val repository: Repository.UserDetail
): ViewModel() {

    private var username = MutableLiveData<String>()

    fun requestUserDetail(username: String){
        this.username.value = username
    }

    val getUserDetail: LiveData<Result<DetailUserResponse>> = username.switchMap {
        liveData { emitSource(repository.getUserDetail(it)) }
    }
}