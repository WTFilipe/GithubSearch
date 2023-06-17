package com.example.githubsearch.ui.user.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.BaseFragment
import com.example.githubsearch.databinding.FragmentUserDetailBinding
import com.example.githubsearch.domain.RepositoryViewModel
import com.example.githubsearch.domain.UserViewModel
import com.example.githubsearch.hide
import com.example.githubsearch.show
import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserUIModel
import com.example.githubsearch.ui.repository.list.RepositoryListAdapter

class UserDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var username: String
    private lateinit var repositoryAdapter: RepositoryListAdapter
    private val userViewModel: UserViewModel by viewModels()
    private val repositoriesViewModel: RepositoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater)

        setupRepositoryRecyclerView()

        return binding.root
    }

    private fun setupRepositoryRecyclerView() {
        repositoryAdapter = RepositoryListAdapter(REPOSITORY_QUANTITY_IN_LIST){repositoryURL ->
            Toast.makeText(context, repositoryURL, Toast.LENGTH_SHORT).show()
        }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvUserRepositories.layoutManager = layoutManager
        binding.rvUserRepositories.adapter = repositoryAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(USERNAME)?.let {
            username = it
            userViewModel.loadUserDetail(username)
            repositoriesViewModel.loadUserRepositories(username)
        }

        setupObservers()
    }

    private fun setupObservers() {
        userViewModel.user.observe(viewLifecycleOwner){
            when(it){
                is UIState.Error -> onError()
                is UIState.Loading -> onLoading()
                is UIState.Success -> onSuccess(it.data)
            }
        }
        repositoriesViewModel.repositoriesList.observe(viewLifecycleOwner){
            when(it){
                is UIState.Error -> onReposError()
                is UIState.Loading -> onReposLoading()
                is UIState.Success -> onReposSuccess(it.data)
            }
        }
    }

    private fun onReposSuccess(data: List<RepositoryOnListUIModel>) {
        repositoryAdapter.setRepositoryList(data)
    }

    private fun onReposLoading() {
    }

    private fun onReposError() {

    }

    private fun onSuccess(data: UserUIModel) {
        binding.tvName.text = data.name
        binding.tvBio.text = data.bio
        binding.loadingLayout.hide()
        binding.errorLayout.hide()
    }

    private fun onLoading() {
        binding.loadingLayout.show()
        binding.errorLayout.hide()
    }

    private fun onError(){
        binding.loadingLayout.hide()
        binding.errorLayout.show()
    }

    companion object {
        const val USERNAME = "USERNAME"
        const val REPOSITORY_QUANTITY_IN_LIST = 5
    }
}