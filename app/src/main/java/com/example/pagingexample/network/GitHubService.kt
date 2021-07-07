package com.example.pagingexample.network

import com.example.pagingexample.data.UserModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("/search/users")
    suspend fun search(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order") order: String? = "asc",
        @Query("sort") sort: String? = "best match"
    ): GitSearchRes

    @GET("/users/{user}")
    suspend fun getUser(@Path("user") user: String): UserModel
}