package com.dicoding.academy.githubuser.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.academy.githubuser.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.networking.ApiService
import com.dicoding.academy.githubuser.utility.Constants.COUNT_OF_PER_PAGE
import com.dicoding.academy.githubuser.utility.Constants.USER_PAGING_STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException

class UserPagingSource(
    private val apiService: ApiService,
    private val query: String
): PagingSource<Int, UserItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        val position = params.key ?: USER_PAGING_STARTING_PAGE_INDEX
        return try {
            val response = apiService.searchUser(query, position, params.loadSize)
            val users = response.items
            val nextKey = if (users.isNullOrEmpty()){
                null
            }else{
                position + (params.loadSize / COUNT_OF_PER_PAGE)
            }
            LoadResult.Page(
                data = users,
                prevKey = if (position == USER_PAGING_STARTING_PAGE_INDEX) null else position -1,
                nextKey = nextKey
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }
}