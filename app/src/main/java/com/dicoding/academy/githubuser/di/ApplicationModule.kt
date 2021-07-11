package com.dicoding.academy.githubuser.di

import com.dicoding.academy.githubuser.data.repository.Repository
import com.dicoding.academy.githubuser.data.repository.UserSearchRepository
import com.dicoding.academy.githubuser.networking.ApiService
import com.dicoding.academy.githubuser.networking.RetrofitBuilder
import com.dicoding.academy.githubuser.ui.MainViewModel
import com.dicoding.academy.githubuser.ui.UserAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val networkModule = module {
        single { RetrofitBuilder.createApiService() }
    }

    val repositoryModule = module {
        fun provideUserSearchRepository(apiService: ApiService): Repository.UserSearch{
            return UserSearchRepository(apiService)
        }

        factory { provideUserSearchRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    val adapterModule = module {
        factory { UserAdapter() }
    }
}