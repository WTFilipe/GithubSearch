package com.example.githubsearch.ui.user.list

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.UserListItemBinding
import com.example.githubsearch.loadCircleImage
import com.example.githubsearch.ui.models.UserOnListUIModel

class UserListViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(user: UserOnListUIModel, onItemClicked: (String, String, ImageView) -> Unit){
        binding.tvName.text = user.login
        binding.ivPhoto.loadCircleImage(user.avatar_url)
        binding.tvUserType.text = user.type
        binding.root.setOnClickListener {
            onItemClicked(user.login, user.avatar_url, binding.ivPhoto)
        }
    }

}