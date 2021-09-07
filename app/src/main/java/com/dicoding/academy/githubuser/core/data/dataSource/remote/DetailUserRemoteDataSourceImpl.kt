package com.dicoding.academy.githubuser.core.data.dataSource.remote

import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.core.data.networking.ApiService
import com.dicoding.academy.githubuser.utility.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException

class DetailUserRemoteDataSourceImpl(
    private val apiService: ApiService,
    private val dispatcher: DispatcherProvider
): RemoteDataSource {
    override suspend fun getDetailUser(username: String): Flow<DetailUserResponse> {
        return flow {
            val request = apiService.detailUser(username)
            emit(request)
        }.retry(3L) {
            return@retry it is HttpException
        }.flowOn(dispatcher.io())
    }
}