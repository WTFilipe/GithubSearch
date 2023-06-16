package com.example.githubsearch.ui.user.list

import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.UserListItemBinding
import com.example.githubsearch.ui.models.UserUI

class UserListViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(user: UserUI){
        binding.userName.text = user.name
    }

}