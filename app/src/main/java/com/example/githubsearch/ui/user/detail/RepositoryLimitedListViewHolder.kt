package com.example.githubsearch.ui.user.detail

import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.databinding.RepositoryLimitedListItemBinding
import com.example.githubsearch.ui.models.RepositoryOnListUIModel

class RepositoryLimitedListViewHolder(private val binding: RepositoryLimitedListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: RepositoryOnListUIModel, onItemClicked: (String) -> Unit) {
        val context = binding.root.context
        binding.tvRepoName.text = repository.name
        binding.tvRepoDesc.text = repository.description.takeIf { it.isNotBlank() } ?: context.getString(R.string.placeholder_no_description)
        binding.tvStars.text = repository.stargazers_count.toString()
        binding.tvWatchers.text = repository.watchers_count.toString()
        binding.root.setOnClickListener {
            onItemClicked(repository.url)
        }
    }
}