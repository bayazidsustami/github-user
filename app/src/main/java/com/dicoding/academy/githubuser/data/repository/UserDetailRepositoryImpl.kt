package com.dicoding.academy.githubuser.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.academy.githubuser.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.utility.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class UserDetailRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): Repository.UserDetail {
    override suspend fun getUserDetail(username: String): LiveData<Result<DetailUserResponse>> {
        val result = MutableLiveData<Result<DetailUserResponse>>()
        remoteDataSource.getDetailUser(username)
            .onStart {
                result.postValue(Result.Loading(data = null))
            }.catch {
                result.postValue(Result.Error(data = null, message = it.message))
            }.collect {
                result.postValue(Result.Success(data = it))
            }
        return result
    }
}