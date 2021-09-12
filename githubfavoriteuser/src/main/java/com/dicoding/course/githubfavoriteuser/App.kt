package com.dicoding.course.githubfavoriteuser

import android.app.Application
import com.dicoding.course.githubfavoriteuser.di.ApplicationModule.dataSourceModule
import com.dicoding.course.githubfavoriteuser.di.ApplicationModule.repositoryModule
import com.dicoding.course.githubfavoriteuser.di.ApplicationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(
                dataSourceModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}