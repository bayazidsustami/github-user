package com.dicoding.academy.githubuser.networking

import com.dicoding.academy.githubuser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    private val okhttpClient: OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            var okhttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original  = chain.request()
                    val request = original.newBuilder()
                        .header("Authorization", BuildConfig.TOKEN)
                        .method(original.method, original.body)
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor { chain ->
                    val url = chain.request()
                        .url
                        .newBuilder()
                        .build()
                    val request = chain.request().newBuilder().url(url).build()
                    chain.proceed(request)
                }

            if (BuildConfig.DEBUG){
                okhttpBuilder = okhttpBuilder.addInterceptor(loggingInterceptor)
            }
            return okhttpBuilder.build()
        }

    private val buildRetrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun createApiService(): ApiService = buildRetrofit.create(ApiService::class.java)

}