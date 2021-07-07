package com.example.anylinecc.main

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.anylinecc.data.UserModel
import com.example.anylinecc.data.UserRepo
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val userRepo: UserRepo
) : ViewModel() {

    fun getUsers(query: String?): Flow<PagingData<UserModel>>? = Pager(PagingConfig(10)) {
        UserPagingSource(userRepo, query ?: "")
    }.flow.cachedIn(viewModelScope)
}