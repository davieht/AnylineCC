package com.example.pagingexample.data

import com.example.pagingexample.UserCache
import com.example.pagingexample.network.GitHubService

class UserRepo(
    private val gitHubService: GitHubService,
    private val userCache: UserCache
) {

    private var lastQuery = ""

    suspend fun getUsers(query: String, nextPageNumber: Int = 1, perPage: Int = 10): List<UserModel> {
        if (lastQuery != query) {
            lastQuery = query
            userCache.clear()
        }

        userCache.putAll(
            gitHubService.search(
                query,
                nextPageNumber,
                perPage,
                null,
                null
            ).items.associateBy { userModel -> userModel.id })
        return userCache.values.toList()
    }

    suspend fun getUser(userId: String): UserModel {
        val cached: UserModel? = userCache.get(userId)
        if (cached != null) {
            return cached
        }
        // This implementation is still suboptimal but better than before.
        // A complete implementation also handles error cases.
        val freshUser = gitHubService.getUser(userId)
        userCache.put(userId, freshUser)
        return freshUser
    }
}