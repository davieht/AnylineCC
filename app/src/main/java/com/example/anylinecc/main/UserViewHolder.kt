package com.example.anylinecc.main

import androidx.recyclerview.widget.RecyclerView
import com.example.anylinecc.data.UserModel
import com.example.anylinecc.databinding.ListItemUserBinding

class UserViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserModel) {
        binding.user = user
    }
}