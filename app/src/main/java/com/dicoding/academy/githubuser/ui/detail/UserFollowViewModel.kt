package com.dicoding.academy.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.domain.useCase.UserFollowUseCase
import kotlinx.coroutines.flow.Flow

class UserFollowViewModel constructor(
    private val useCase: UserFollowUseCase
): ViewModel() {
    private var currentUsername: String? = null
    private var currentResult: Flow<PagingData<UserItem>>? = null

    fun getUserFollow(username: String, index: Int): Flow<PagingData<UserItem>>{
        val lastResult = currentResult
        if (username == currentUsername && lastResult != null){
            return lastResult
        }

        val newResult: Flow<PagingData<UserItem>> = useCase.getUserFollow(index, username)
            .cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}