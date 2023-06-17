package com.example.githubsearch.ui.repository.list

import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.RepositoryListItemBinding
import com.example.githubsearch.ui.models.RepositoryOnListUIModel

class RepositoryListViewHolder(private val binding: RepositoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: RepositoryOnListUIModel, onItemClicked: (String) -> Unit) {
        binding.tvRepoName.text = repository.name
        binding.tvRepoDesc.text = repository.description
        binding.root.setOnClickListener {
            onItemClicked(repository.url)
        }
    }
}