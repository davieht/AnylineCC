package com.example.pagingexample.data

import com.google.gson.annotations.SerializedName

data class UserModel(
    val login: String,
    val id: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    @SerializedName("html_url")
    val htmlUrl: String
)