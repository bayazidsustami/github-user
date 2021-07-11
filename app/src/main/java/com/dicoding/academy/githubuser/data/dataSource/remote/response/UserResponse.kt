package com.dicoding.academy.githubuser.data.dataSource.remote.response

import com.squareup.moshi.Json

data class UserResponse(

	@Json(name="total_count")
	val totalCount: Int? = null,

	@Json(name="incomplete_results")
	val incompleteResults: Boolean? = null,

	@Json(name="items")
	val items: List<UserItem> = emptyList()
)