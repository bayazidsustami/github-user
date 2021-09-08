package com.dicoding.academy.githubuser.core.data.repository

import com.dicoding.academy.githubuser.core.common.DataMapper.mapDetailToDomain
import com.dicoding.academy.githubuser.core.common.DataMapper.mapDetailToEntity
import com.dicoding.academy.githubuser.core.common.DataMapper.mapResponseDetailToDomain
import com.dicoding.academy.githubuser.core.data.dataSource.local.LocalDataSource
import com.dicoding.academy.githubuser.core.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.core.domain.repository.UserDetailRepository
import com.dicoding.academy.githubuser.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserDetailRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): UserDetailRepository {
    override suspend fun getUserDetail(username: String): Flow<Result<DetailUserUIModel>> {
        if (localDataSource.getUserIfExists(username).first()){
            return localDataSource.getUserDetail(username).map {
                Result.Success(it.mapDetailToDomain())
            }
        }else{
            return remoteDataSource.getDetailUser(username).map {
                when(it){
                    is Result.Loading -> {
                        Result.Loading(data = null)
                    }
                    is Result.Success -> {
                        Result.Success(data = it.data.mapResponseDetailToDomain())
                    }
                    is Result.Error -> {
                        Result.Error<DetailUserUIModel>(code = it.code, message = it.message, data = null)
                    }
                }
            }
        }
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