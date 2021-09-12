package com.dicoding.course.githubfavoriteuser.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.dicoding.course.githubfavoriteuser.data.UserModel

class ProviderHelper constructor(
    private val context: Context
) {
    private companion object{
        const val AUTHORITY = "com.dicoding.academy.githubuser.provider"
        val CONTENT_URI: Uri = Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath("detail_user_entity")
            .build()
    }

    @SuppressLint("Recycle")
    fun getAllFavoriteUser(): List<UserModel>{
        val providerClient = context.contentResolver?.acquireContentProviderClient(CONTENT_URI)
        var cursor: Cursor? = null
        try {
            cursor = providerClient?.query(
                CONTENT_URI,
                null,
                null,
                null,
                null)
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            providerClient?.close()
            cursor?.close()
        }

        return cursor.mapCursorToList()
    }

    private fun Cursor?.mapCursorToList(): List<UserModel>{
        val result = mutableListOf<UserModel>()
        if (this != null){
            while (moveToNext()){
                val model = UserModel(
                    id = getInt(getColumnIndexOrThrow("id")),
                    login = getString(getColumnIndexOrThrow("login")),
                    company = getString(getColumnIndexOrThrow("company")),
                    publicRepos = getInt(getColumnIndexOrThrow("public_repos")),
                    followers = getInt(getColumnIndexOrThrow("followers")),
                    following = getInt(getColumnIndexOrThrow("following")),
                    avatarUrl = getString(getColumnIndexOrThrow("avatar_url")),
                    name = getString(getColumnIndexOrThrow("name")),
                    location = getString(getColumnIndexOrThrow("location")),
                    isFavorite = getInt(getColumnIndexOrThrow("isFavorite")) == 1
                )
                result.add(model)
            }
        }
        return result
    }
}