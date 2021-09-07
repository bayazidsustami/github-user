package com.dicoding.academy.githubuser.core.di

import com.dicoding.academy.githubuser.core.data.dataSource.remote.DetailUserRemoteDataSourceImpl
import com.dicoding.academy.githubuser.core.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.core.data.repository.Repository
import com.dicoding.academy.githubuser.core.data.repository.UserDetailRepositoryImpl
import com.dicoding.academy.githubuser.core.data.repository.UserFollowRepositoryImpl
import com.dicoding.academy.githubuser.core.data.repository.UserSearchRepositoryImpl
import com.dicoding.academy.githubuser.core.data.networking.ApiService
import com.dicoding.academy.githubuser.core.data.networking.RetrofitBuilder
import com.dicoding.academy.githubuser.ui.main.MainViewModel
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.detail.DetailUserViewModel
import com.dicoding.academy.githubuser.ui.detail.UserFollowViewModel
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
            return UserSearchRepositoryImpl(apiService)
        }

        fun provideDetailUserRepository(remoteDataSource: RemoteDataSource): Repository.UserDetail{
            return UserDetailRepositoryImpl(remoteDataSource)
        }

        fun provideUserFollowRepository(apiService: ApiService): Repository.UserFollow{
            return UserFollowRepositoryImpl(apiService)
        }

        factory { provideUserSearchRepository(get()) }
        factory { provideDetailUserRepository(get()) }
        factory { provideUserFollowRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { DetailUserViewModel(get()) }
        viewModel { UserFollowViewModel(get()) }
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