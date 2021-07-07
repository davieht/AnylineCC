package com.example.pagingexample.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingexample.data.UserModel
import com.example.pagingexample.databinding.ListItemUserBinding

class UserPagingAdapter(diffCallback: DiffUtil.ItemCallback<UserModel>) : PagingDataAdapter<UserModel, UserViewHolder>(diffCallback) {
    private var onItemClickListener : ((UserModel) -> Unit)? = null
    private var onUpdateListener: ((Int) -> Unit)? = null

    /**
     * Item Click Listener for navigation
     */
    fun setOnItemClickListener(l: ((UserModel) -> Unit)) {
        this.onItemClickListener = l
    }

    /**
     * Update listener to evaluate the item count
     */
    fun setOnUpdateListener(l: ((Int) -> Unit)) {
        this.onUpdateListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                this.onItemClickListener?.invoke(item)
            }
        }
    }

    // This is not optimal but enough for debug purposes
    override fun getItemCount(): Int {
        val count = super.getItemCount()
        onUpdateListener?.invoke(count)
        return count
    }
}