package com.dicoding.course.githubfavoriteuser.di

import com.dicoding.course.githubfavoriteuser.data.dataSource.ProviderDataSource
import com.dicoding.course.githubfavoriteuser.data.dataSource.ProviderDataSourceImpl
import com.dicoding.course.githubfavoriteuser.data.repository.UserRepository
import com.dicoding.course.githubfavoriteuser.data.repository.UserRepositoryImpl
import com.dicoding.course.githubfavoriteuser.data.utils.ProviderHelper
import com.dicoding.course.githubfavoriteuser.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val dataSourceModule = module {
        single { ProviderHelper(androidContext()) }

        fun provideDataSource(providerHelper: ProviderHelper): ProviderDataSource{
            return ProviderDataSourceImpl(providerHelper)
        }
        factory { provideDataSource(get()) }
    }

    val repositoryModule = module {
        fun provideRepositoryModule(dataSource: ProviderDataSource): UserRepository{
            return UserRepositoryImpl(dataSource)
        }

        factory { provideRepositoryModule(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }
}