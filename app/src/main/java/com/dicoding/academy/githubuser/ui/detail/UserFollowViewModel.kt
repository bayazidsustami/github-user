package com.dicoding.academy.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class UserFollowViewModel(
    private val repository: Repository.UserFollow
): ViewModel() {
    private var currentUsername: String? = null
    private var currentResult: Flow<PagingData<UserItem>>? = null

    fun getUserFollow(username: String, index: Int): Flow<PagingData<UserItem>>{
        val lastResult = currentResult
        if (username == currentUsername && lastResult != null){
            return lastResult
        }

        val newResult: Flow<PagingData<UserItem>> = repository.getUserFollow(index, username)
            .cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}