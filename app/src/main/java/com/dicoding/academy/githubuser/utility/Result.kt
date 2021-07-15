package com.dicoding.academy.githubuser.utility

sealed class Result<out T>{
    data class Loading<T>(val data: T? = null): Result<T>()
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val code: Int? = null, val data: T?= null, val message: String? = null): Result<T>()
}
