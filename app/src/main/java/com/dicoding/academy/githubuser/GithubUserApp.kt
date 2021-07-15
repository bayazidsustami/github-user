package com.dicoding.academy.githubuser

import android.app.Application
import com.dicoding.academy.githubuser.di.ApplicationModule.adapterModule
import com.dicoding.academy.githubuser.di.ApplicationModule.dispatcherModule
import com.dicoding.academy.githubuser.di.ApplicationModule.networkModule
import com.dicoding.academy.githubuser.di.ApplicationModule.remoteDataSourceModule
import com.dicoding.academy.githubuser.di.ApplicationModule.repositoryModule
import com.dicoding.academy.githubuser.di.ApplicationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GithubUserApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@GithubUserApp)
            modules(
                listOf(
                    networkModule,
                    remoteDataSourceModule,
                    repositoryModule,
                    viewModelModule,
                    adapterModule,
                    dispatcherModule
                )
            )
        }
    }
}