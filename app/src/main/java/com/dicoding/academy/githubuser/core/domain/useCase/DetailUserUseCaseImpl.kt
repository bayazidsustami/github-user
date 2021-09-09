package com.dicoding.academy.githubuser.core.domain.useCase

import androidx.paging.PagingData
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.core.domain.repository.UserDetailRepository
import com.dicoding.academy.githubuser.utility.Result
import kotlinx.coroutines.flow.Flow

class DetailUserUseCaseImpl constructor(
    private val repository: UserDetailRepository
): DetailUserUseCase {
    override suspend fun getUserDetail(username: String): Flow<Result<DetailUserUIModel>> {
        return repository.getUserDetail(username)
    }

    override fun getUser(username: String): Flow<DetailUserUIModel> {
        return repository.getUser(username)
    }

    override suspend fun saveUser(user: DetailUserUIModel) {
        repository.saveUser(user)
    }

    override fun getUserIfExists(username: String): Flow<Boolean> {
        return repository.getUserIfExists(username)
    }

    override fun getAllUser(): Flow<PagingData<DetailUserUIModel>> {
        return repository.getAllUser()
    }
}