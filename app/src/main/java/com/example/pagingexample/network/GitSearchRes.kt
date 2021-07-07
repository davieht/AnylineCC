package com.example.pagingexample.network

import com.example.pagingexample.data.UserModel
import com.google.gson.annotations.SerializedName

data class GitSearchRes(
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<UserModel>
)