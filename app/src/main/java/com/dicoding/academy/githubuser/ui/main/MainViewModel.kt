package com.dicoding.academy.githubuser.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.academy.githubuser.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val repository: Repository.UserSearch
): ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<UserItem>>? = null

    fun searchUser(query: String): Flow<PagingData<UserItem>>{
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null){
            return lastResult
        }
        currentQueryValue = query

        val newResult: Flow<PagingData<UserItem>> = repository.getUserSearch(query)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}