package com.example.githubsearch.ui.repository.list

import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.databinding.RepositoryListItemBinding
import com.example.githubsearch.ui.models.RepositoryOnListUIModel

class RepositoryListViewHolder(private val binding: RepositoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

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