package com.dicoding.academy.githubuser.core.data.dataSource.remote.response

import com.squareup.moshi.Json

data class UserResponse(

	@field:Json(name="total_count")
	val totalCount: Int? = null,

	@field:Json(name="incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:Json(name="items")
	val items: List<UserItem> = emptyList()
)