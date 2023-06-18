package com.example.githubsearch.ui.user.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.databinding.RepositoryLimitedListItemBinding
import com.example.githubsearch.ui.models.RepositoryOnListUIModel

class RepositoryLimitedListAdapter(private val maxItemsQuantity: Int? = null, private val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<RepositoryLimitedListViewHolder>(){

    private val repositoryList = mutableListOf<RepositoryOnListUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryLimitedListViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = RepositoryLimitedListItemBinding.inflate(inflater)

        return RepositoryLimitedListViewHolder(binding)
    }

    override fun getItemCount(): Int = if (maxItemsQuantity == null || (maxItemsQuantity != null && maxItemsQuantity > repositoryList.size)){
        repositoryList.size
    } else {
        maxItemsQuantity
    }

    override fun onBindViewHolder(holder: RepositoryLimitedListViewHolder, position: Int) {
        val repository = repositoryList[position]

        holder.bind(repository, onItemClicked)
    }
    fun setRepositoryList(list: List<RepositoryOnListUIModel>){
        this.repositoryList.addAll(list)
        notifyDataSetChanged()
    }

}