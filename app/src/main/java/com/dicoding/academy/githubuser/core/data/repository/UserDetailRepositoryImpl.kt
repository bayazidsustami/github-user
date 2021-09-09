package com.dicoding.academy.githubuser.core.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.common.DataMapper.mapDetailToDomain
import com.dicoding.academy.githubuser.core.common.DataMapper.mapDetailToEntity
import com.dicoding.academy.githubuser.core.common.DataMapper.mapResponseDetailToDomain
import com.dicoding.academy.githubuser.core.data.dataSource.local.LocalDataSource
import com.dicoding.academy.githubuser.core.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.core.domain.repository.UserDetailRepository
import com.dicoding.academy.githubuser.utility.Result
import kotlinx.coroutines.flow.*

class UserDetailRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): UserDetailRepository {
    override suspend fun getUserDetail(username: String): Flow<Result<DetailUserUIModel>> {
        return flow {
            emit(Result.Loading(null))
            val isExists = localDataSource.getUserIfExists(username).first()
            if (isExists){
                Log.d("DATAS", "LOCALS $isExists")
                emitAll(localDataSource.getUserDetail(username).map {
                    Log.d("DATAS", "LOCALSM $isExists")
                    Result.Success(it.mapDetailToDomain())
                })
            }else{
                remoteDataSource.getDetailUser(username).collect {
                    when(it){
                        is Result.Success -> {
                            Log.d("DATAS", it.data.toString())
                            emit(Result.Success(it.data.mapResponseDetailToDomain()))
                        }
                        is Result.Error -> {
                            emit(Result.Error(it.code, null, it.message))
                        }
                        is Result.Loading -> {
                            emit(Result.Loading(null))
                        }
                    }
                }
            }
        }
    }

    override fun getAllUser(): Flow<PagingData<DetailUserUIModel>> {
        val pagingSource = localDataSource.getAllFavoriteUser().map {
            it.mapDetailToDomain()
        }.asPagingSourceFactory()
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSource
        ).flow
    }

    override fun getUser(username: String): Flow<DetailUserUIModel> {
        return localDataSource.getUserDetail(username).map { it.mapDetailToDomain() }
    }

    override suspend fun saveUser(user: DetailUserUIModel) {
        return localDataSource.saveUser(user.mapDetailToEntity())
    }

    override fun getUserIfExists(username: String): Flow<Boolean> {
        return localDataSource.getUserIfExists(username)
    }
}