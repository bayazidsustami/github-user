package com.dicoding.academy.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.core.domain.useCase.DetailUserUseCase
import com.dicoding.academy.githubuser.utility.Result
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

}