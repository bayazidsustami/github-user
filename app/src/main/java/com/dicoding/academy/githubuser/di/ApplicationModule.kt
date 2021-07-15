package com.dicoding.academy.githubuser.di

import com.dicoding.academy.githubuser.data.dataSource.remote.DetailUserRemoteDataSourceImpl
import com.dicoding.academy.githubuser.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.data.repository.Repository
import com.dicoding.academy.githubuser.data.repository.UserDetailRepositoryImpl
import com.dicoding.academy.githubuser.data.repository.UserSearchRepository
import com.dicoding.academy.githubuser.networking.ApiService
import com.dicoding.academy.githubuser.networking.RetrofitBuilder
import com.dicoding.academy.githubuser.ui.main.MainViewModel
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.detail.DetailUserViewModel
import com.dicoding.academy.githubuser.utility.AppDispatcher
import com.dicoding.academy.githubuser.utility.DispatcherProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val networkModule = module {
        single { RetrofitBuilder.createApiService() }
    }

    val remoteDataSourceModule = module {
        fun provideDetailUserRemoteDataSource(
            apiService: ApiService,
            dispatcher: DispatcherProvider
        ): RemoteDataSource{
            return DetailUserRemoteDataSourceImpl(apiService, dispatcher)
        }

        factory { provideDetailUserRemoteDataSource(get(), get()) }
    }

    val repositoryModule = module {
        fun provideUserSearchRepository(apiService: ApiService): Repository.UserSearch{
            return UserSearchRepository(apiService)
        }

        fun provideDetailUserRepository(remoteDataSource: RemoteDataSource): Repository.UserDetail{
            return UserDetailRepositoryImpl(remoteDataSource)
        }

        factory { provideUserSearchRepository(get()) }
        factory { provideDetailUserRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { DetailUserViewModel(get()) }
    }

    val adapterModule = module {
        factory { UserAdapter() }
    }

    val dispatcherModule = module {
        fun provideDispatcherProvider(): DispatcherProvider{
            return AppDispatcher()
        }
        factory { provideDispatcherProvider() }
    }
}