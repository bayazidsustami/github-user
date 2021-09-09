package com.dicoding.academy.githubuser.core.data.dataSource.contentProvider

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.dicoding.academy.githubuser"
    const val SCHEME = "content"

    class UserColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "detail_user_entity"
            const val _ID = "id"
            const val NAME = "login"
            const val TYPE = "type"
            const val IMG_URL = "avatarUrl"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}