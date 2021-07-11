package com.dicoding.academy.githubuser.di

import com.dicoding.academy.githubuser.networking.RetrofitBuilder
import org.koin.dsl.module

object ApplicationModule {
    val networkModule = module {
        single { RetrofitBuilder.createApiService() }
    }
}