package com.example.githubsearch.ui.repository.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.BaseFragment
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentRepositoryListBinding
import com.example.githubsearch.domain.RepositoryViewModel
import com.example.githubsearch.hide
import com.example.githubsearch.openUrlInBrowser
import com.example.githubsearch.show
import com.example.githubsearch.ui.SpaceVerticalItemDecoration
import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.user.detail.UserDetailFragment
import java.net.UnknownHostException

class RepositoryListFragment : BaseFragment() {
    private lateinit var binding: FragmentRepositoryListBinding
    private lateinit var username: String
    private lateinit var repositoryAdapter: RepositoryListAdapter
    private val repositoriesViewModel: RepositoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        arguments?.getString(UserDetailFragment.USERNAME)?.let {
            username = it
            binding.repositoryListTitle.text = getString(R.string.title_repositories, username)
            loadData()
        } ?: onError()
    }

    private fun setupObservers() {
        repositoriesViewModel.repositoriesList.observe(viewLifecycleOwner){
            when(it){
                is UIState.Error -> onError(it.cause)
                is UIState.Loading -> onLoading()
                is UIState.Success -> onSuccess(it.data)
            }
        }
    }

    private fun onSuccess(data: List<RepositoryOnListUIModel>) {
        repositoryAdapter = RepositoryListAdapter { repositoryURL ->
            context?.let { repositoryURL.openUrlInBrowser(it) }
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val spacerItemDecoration = SpaceVerticalItemDecoration(8)

        binding.repositoryList.layoutManager = layoutManager
        binding.repositoryList.adapter = repositoryAdapter
        binding.repositoryList.addItemDecoration(spacerItemDecoration)

        repositoryAdapter.setRepositoryList(data)

        binding.repositoryList.show()
        binding.errorLayout.root.hide()
        binding.emptyLayout.root.hide()
        binding.loadingLayout.hide()
    }

    private fun onLoading() {
        binding.repositoryList.hide()
        binding.errorLayout.root.hide()
        binding.emptyLayout.root.hide()
        binding.loadingLayout.show()
    }

    private fun onError(cause: Throwable? = null){
        if (cause is UnknownHostException){
            binding.errorLayout.errorText.text = getString(R.string.feedback_no_internet_connection)
        } else {
            binding.errorLayout.errorText.text = getString(R.string.feedback_generic_error)
        }
        binding.errorLayout.root.show()
        binding.errorLayout.root.setOnClickListener {
            loadData()
        }
        binding.loadingLayout.hide()
        binding.repositoryList.hide()
    }

    private fun loadData() {
        repositoriesViewModel.loadUserRepositories(username)
    }

    companion object {
        const val USERNAME = "USERNAME"
    }

}