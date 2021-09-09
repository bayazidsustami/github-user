package com.dicoding.academy.githubuser.ui.favoite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.dicoding.academy.githubuser.core.common.DataMapper.mapToFitAdapterUser
import com.dicoding.academy.githubuser.core.domain.useCase.DetailUserUseCase
import kotlinx.coroutines.flow.map

class FavoriteViewModel constructor(
    useCase: DetailUserUseCase
): ViewModel() {

    val favoriteUser = useCase.getAllUser().map { pagingData ->
        pagingData.map {it.mapToFitAdapterUser()}
    }.cachedIn(viewModelScope)
}