package com.dicoding.academy.githubuser.core.data.dataSource.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.academy.githubuser.core.data.dataSource.contentProvider.DatabaseContract.AUTHORITY
import com.dicoding.academy.githubuser.core.data.dataSource.contentProvider.DatabaseContract.UserColumns.Companion.TABLE_NAME
import com.dicoding.academy.githubuser.core.data.dataSource.local.room.GithubDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserFavoriteProvider : ContentProvider(), KoinComponent {

    private val database: GithubDatabase by inject()

    companion object{
        private const val USER = 1
        private const val USER_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, USER)
            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", USER_ID)
        }
    }

    override fun onCreate(): Boolean = true

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