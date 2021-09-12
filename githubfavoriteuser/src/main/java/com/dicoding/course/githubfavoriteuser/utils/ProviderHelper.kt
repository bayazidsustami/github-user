package com.dicoding.course.githubfavoriteuser.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.dicoding.course.githubfavoriteuser.UserModel

class ProviderHelper(context: Context) {
    companion object{
        private const val AUTHORITY = "com.dicoding.academy.githubuser.provider"
        private val CONTENT_URI: Uri = Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath("detail_user_entity")
            .build()
    }

    init {
        val providerClient = context.contentResolver?.acquireContentProviderClient(CONTENT_URI)
        var cursor: Cursor? = null
        try {
            cursor = providerClient?.query(
                CONTENT_URI,
                null,
                null,
                null,
                null)
            Log.d("DATAS", cursor.mapCursorToList().toString())
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            cursor?.close()
        }
    }

    private fun Cursor?.mapCursorToList(): List<UserModel>{
        val result = mutableListOf<UserModel>()
        if (this != null){
            while (this.moveToNext()){
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