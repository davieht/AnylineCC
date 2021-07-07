package com.example.pagingexample.detail

import androidx.lifecycle.*
import com.example.pagingexample.data.UserModel
import com.example.pagingexample.data.UserRepo

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val userRepo: UserRepo
) : ViewModel(){
    val user : LiveData<UserModel> = savedStateHandle.getLiveData<String>("userId").switchMap { userId ->
        liveData {
            emit(userRepo.getUser(userId))
        }
    }

    fun setUserId(userId: String?) {
        savedStateHandle["userId"] = userId
    }
}