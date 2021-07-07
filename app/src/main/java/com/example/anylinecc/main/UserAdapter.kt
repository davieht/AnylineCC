package com.example.anylinecc.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anylinecc.data.UserModel
import com.example.anylinecc.databinding.ListItemUserBinding
import com.example.anylinecc.utils.BindableAdapter

class UserAdapter : RecyclerView.Adapter<UserViewHolder>(), BindableAdapter<List<UserModel>> {

    private var userList = emptyList<UserModel>()
    private var onItemClickListener : ((UserModel) -> Unit)? = null

    override fun setData(data: List<UserModel>?) {
        userList = data ?: emptyList()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(l: ((UserModel) -> Unit)) {
        this.onItemClickListener = l
    }

//    override fun addData(data: List<UserModel>?) {
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            this.onItemClickListener?.invoke(userList[position])
        }
    }
}