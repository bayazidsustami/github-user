package com.dicoding.academy.githubuser

import android.app.Application
import com.dicoding.academy.githubuser.core.di.ApplicationModule.adapterModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.databaseModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.dispatcherModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.domainModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.localDataSourceModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.networkModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.remoteDataSourceModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.repositoryModule
import com.dicoding.academy.githubuser.core.di.ApplicationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class GithubUserApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@GithubUserApp)
            modules(
                networkModule,
                databaseModule,
                localDataSourceModule,
                remoteDataSourceModule,
                repositoryModule,
                domainModule,
                viewModelModule,
                adapterModule,
                dispatcherModule
            )
        }
    }
}