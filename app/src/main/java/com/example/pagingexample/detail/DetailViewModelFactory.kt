package com.example.pagingexample.detail

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pagingexample.data.UserRepo

class DetailViewModelFactory(
    private val owner: SavedStateRegistryOwner,
    private val repo: UserRepo
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return DetailViewModel(handle, repo) as T
    }
}