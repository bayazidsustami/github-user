package com.dicoding.academy.githubuser.core.common

import com.dicoding.academy.githubuser.core.data.dataSource.local.entity.DetailUserEntity
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.DetailUserResponse
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel

object DataMapper {

    fun DetailUserEntity.mapDetailToDomain(): DetailUserUIModel{
        return DetailUserUIModel(
            id, login, company, publicRepos, followers, avatarUrl, following, name, location, isFavorite
        )
    }

    fun DetailUserUIModel.mapDetailToEntity(): DetailUserEntity{
        return DetailUserEntity(
            id, login, company, publicRepos, followers, avatarUrl, following, name, location, isFavorite
        )
    }

    fun DetailUserResponse.mapResponseDetailToDomain(): DetailUserUIModel{
        return DetailUserUIModel(
            id = id ?: 0,
            login = login!!,
            company = company,
            publicRepos = publicRepos,
            followers = followers,
            following = following,
            name = name!!,
            avatarUrl = avatarUrl,
            location = location,
            isFavorite = false
        )
    }
}