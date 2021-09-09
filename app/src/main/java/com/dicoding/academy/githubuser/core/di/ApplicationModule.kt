package com.dicoding.academy.githubuser.core.di

import androidx.room.Room
import com.dicoding.academy.githubuser.core.data.dataSource.local.DetailUserLocalDataSourceImpl
import com.dicoding.academy.githubuser.core.data.dataSource.local.LocalDataSource
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.GithubDatabase
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.dao.DetailUserDao
import com.dicoding.academy.githubuser.core.data.dataSource.remote.DetailUserRemoteDataSourceImpl
import com.dicoding.academy.githubuser.core.data.dataSource.remote.RemoteDataSource
import com.dicoding.academy.githubuser.core.data.repository.UserDetailRepositoryImpl
import com.dicoding.academy.githubuser.core.data.repository.UserFollowRepositoryImpl
import com.dicoding.academy.githubuser.core.data.repository.UserSearchRepositoryImpl
import com.dicoding.academy.githubuser.core.data.dataSource.remote.networking.ApiService
import com.dicoding.academy.githubuser.core.data.dataSource.remote.networking.RetrofitBuilder
import com.dicoding.academy.githubuser.core.domain.repository.UserDetailRepository
import com.dicoding.academy.githubuser.core.domain.repository.UserFollowRepository
import com.dicoding.academy.githubuser.core.domain.repository.UserSearchRepository
import com.dicoding.academy.githubuser.core.domain.useCase.DetailUserUseCase
import com.dicoding.academy.githubuser.core.domain.useCase.DetailUserUseCaseImpl
import com.dicoding.academy.githubuser.ui.main.MainViewModel
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.detail.DetailUserViewModel
import com.dicoding.academy.githubuser.ui.detail.UserFollowViewModel
import com.dicoding.academy.githubuser.ui.favoite.FavoriteViewModel
import com.dicoding.academy.githubuser.utility.AppDispatcher
import com.dicoding.academy.githubuser.utility.DispatcherProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val networkModule = module {
        single { RetrofitBuilder.createApiService() }
    }

    val databaseModule = module {
        factory { get<GithubDatabase>().detailUserDao() }

        single {
            Room.databaseBuilder(
                androidContext(),
                GithubDatabase::class.java,
                "Github.db",
            ).fallbackToDestructiveMigration().build()
        }
    }

    val localDataSourceModule = module {
        fun providerDetailUserLocalDataSource(userDao: DetailUserDao): LocalDataSource {
            return DetailUserLocalDataSourceImpl(userDao)
        }

        factory { providerDetailUserLocalDataSource(get()) }
    }

    val remoteDataSourceModule = module {
        fun provideDetailUserRemoteDataSource(
            apiService: ApiService,
            dispatcher: DispatcherProvider
        ): RemoteDataSource {
            return DetailUserRemoteDataSourceImpl(apiService, dispatcher)
        }

        factory { provideDetailUserRemoteDataSource(get(), get()) }
    }

    val repositoryModule = module {
        fun provideUserSearchRepository(apiService: ApiService): UserSearchRepository {
            return UserSearchRepositoryImpl(apiService)
        }

        fun provideDetailUserRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): UserDetailRepository {
            return UserDetailRepositoryImpl(remoteDataSource, localDataSource)
        }

        fun provideUserFollowRepository(apiService: ApiService): UserFollowRepository {
            return UserFollowRepositoryImpl(apiService)
        }

        factory { provideUserSearchRepository(get()) }
        factory { provideDetailUserRepository(get(), get()) }
        factory { provideUserFollowRepository(get()) }
    }

    val domainModule = module {
        fun provideDetailUserUseCase(repository: UserDetailRepository): DetailUserUseCase {
            return DetailUserUseCaseImpl(repository)
        }

        factory { provideDetailUserUseCase(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { DetailUserViewModel(get()) }
        viewModel { UserFollowViewModel(get()) }
        viewModel { FavoriteViewModel(get()) }
    }

    val adapterModule = module {
        factory { UserAdapter() }
    }

    val dispatcherModule = module {
        fun provideDispatcherProvider(): DispatcherProvider {
            return AppDispatcher()
        }
        factory { provideDispatcherProvider() }
    }
}