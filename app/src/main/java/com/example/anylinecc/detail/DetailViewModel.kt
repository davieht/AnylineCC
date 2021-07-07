package com.example.anylinecc.detail

import androidx.lifecycle.*
import com.example.anylinecc.data.UserModel
import com.example.anylinecc.data.UserRepo

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