package com.dicoding.academy.githubuser.ui.detail

import androidx.lifecycle.*
import com.dicoding.academy.githubuser.core.common.Result
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.core.domain.useCase.DetailUserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val useCase: DetailUserUseCase
) : ViewModel() {

    private var _getUserDetail = MutableLiveData<Result<DetailUserUIModel>>()
    val getUserDetail: LiveData<Result<DetailUserUIModel>> get() = _getUserDetail

    fun requestUserDetail(username: String) {
        viewModelScope.launch {
            useCase.getUserDetail(username).collect {
                when (it) {
                    is Result.Loading -> {
                        _getUserDetail.postValue(Result.Loading(null))
                    }
                    is Result.Success -> {
                        _getUserDetail.postValue(Result.Success(it.data))
                    }
                    is Result.Error -> {
                        _getUserDetail.postValue(
                            Result.Error(
                                code = it.code,
                                message = it.message,
                                data = null
                            )
                        )
                    }
                }
            }
        }
    }

    fun saveUser(user: DetailUserUIModel){
        viewModelScope.launch {
            useCase.saveUser(user)
        }
    }

    private var userId = MutableLiveData<String>()

    fun setUserId(username: String){
        userId.value = username
    }

    val isFavoriteUser = userId.switchMap {
        useCase.getUserIfExists(it).asLiveData()
    }

}