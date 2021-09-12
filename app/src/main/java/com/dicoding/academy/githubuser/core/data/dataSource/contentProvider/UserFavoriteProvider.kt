package com.dicoding.academy.githubuser.core.data.dataSource.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.GithubDatabase

class UserFavoriteProvider : ContentProvider(){

    private lateinit var database: GithubDatabase

    companion object{
        private const val AUTHORITY = "com.dicoding.academy.githubuser.provider"
        private const val TABLE_NAME = "detail_user_entity"
        private const val USER = 1
        private const val USER_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, USER)
        }
    }

    override fun onCreate(): Boolean{
        database = Room.databaseBuilder(
            requireNotNull(context),
            GithubDatabase::class.java,
            "Github.db",
        ).build()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor?{
        val db = database.detailUserDao()
        return when(sUriMatcher.match(uri)){
            USER -> db.selectAll()
            USER_ID -> db.selectByName(selection.toString())
            else -> null
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}