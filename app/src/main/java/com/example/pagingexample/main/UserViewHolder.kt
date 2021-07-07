package com.example.pagingexample.main

import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.data.UserModel
import com.example.pagingexample.databinding.ListItemUserBinding

class UserViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserModel) {
        binding.user = user
    }
}