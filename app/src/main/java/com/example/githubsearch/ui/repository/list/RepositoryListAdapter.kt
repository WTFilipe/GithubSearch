package com.example.githubsearch.ui.repository.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.RepositoryListItemBinding
import com.example.githubsearch.ui.models.RepositoryOnListUIModel

class RepositoryListAdapter(private val maxItemsQuantity: Int? = null, private val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<RepositoryListViewHolder>(){

    private val repositoryList = mutableListOf<RepositoryOnListUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = RepositoryListItemBinding.inflate(inflater)

        return RepositoryListViewHolder(binding)
    }

    override fun getItemCount(): Int = if (maxItemsQuantity == null || (maxItemsQuantity != null && maxItemsQuantity > repositoryList.size)){
        repositoryList.size
    } else {
        maxItemsQuantity
    }

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        val repository = repositoryList[position]

        holder.bind(repository, onItemClicked)
    }
    fun setRepositoryList(list: List<RepositoryOnListUIModel>){
        this.repositoryList.addAll(list)
        notifyDataSetChanged()
    }
}