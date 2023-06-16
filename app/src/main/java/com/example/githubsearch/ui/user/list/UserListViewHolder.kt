package com.example.githubsearch.ui.user.list

import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.UserListItemBinding
import com.example.githubsearch.ui.models.UserOnListUIModel

class UserListViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(user: UserOnListUIModel){
        binding.tvName.text = user.login
    }

}