package com.dicoding.githubapp

import com.google.gson.annotations.SerializedName

data class GithubResponse(

	@field:SerializedName("items")
	val items: ArrayList<ItemsItem>
)