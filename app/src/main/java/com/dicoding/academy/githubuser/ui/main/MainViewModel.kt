package com.dicoding.academy.githubuser.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.core.domain.repository.UserSearchRepository
import com.dicoding.academy.githubuser.core.domain.useCase.UserSearchUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel constructor(
    private val useCase: UserSearchUseCase
): ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<UserItem>>? = null

    fun searchUser(query: String): Flow<PagingData<UserItem>>{
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null){
            return lastResult
        }
        currentQueryValue = query

        val newResult: Flow<PagingData<UserItem>> = useCase.searchUser(query)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}