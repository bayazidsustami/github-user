package com.dicoding.academy.githubuser.core.data.dataSource.remote

import android.util.Log
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.core.data.dataSource.remote.networking.ApiService
import com.dicoding.academy.githubuser.utility.DispatcherProvider
import com.dicoding.academy.githubuser.core.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException

class DetailUserRemoteDataSourceImpl(
    private val apiService: ApiService,
    private val dispatcher: DispatcherProvider
): RemoteDataSource {
    override suspend fun getDetailUser(username: String): Flow<Result<DetailUserResponse>> {
        Log.d("DATAS", "REMOTE")
        return flow {
            emit(Result.Loading(data = null))
            try {
                val response = apiService.detailUser(username)
                emit(Result.Success(response))
            }catch (e: HttpException){
                emit(Result.Error(code = e.code(), message = e.message(), data = null))
            }catch (e: Exception){
                emit(Result.Error(code = null, message = e.message, data = null))
            }
        }.retry(3L) {
            return@retry it is HttpException
        }.flowOn(dispatcher.io())
    }
}