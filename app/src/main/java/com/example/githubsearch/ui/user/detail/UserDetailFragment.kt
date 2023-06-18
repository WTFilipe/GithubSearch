package com.example.githubsearch.ui.user.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.example.githubsearch.BaseFragment
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentUserDetailBinding
import com.example.githubsearch.domain.RepositoryViewModel
import com.example.githubsearch.domain.UserViewModel
import com.example.githubsearch.hide
import com.example.githubsearch.loadCircleImage
import com.example.githubsearch.openUrlInBrowser
import com.example.githubsearch.sendEmailToThisAddress
import com.example.githubsearch.show
import com.example.githubsearch.ui.SpaceStartAndEndItemDecoration
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
    }
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
            context?.let { repositoryURL.openUrlInBrowser(it) }
        }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val spacerItemDecoration = SpaceStartAndEndItemDecoration(16, 16)

        binding.rvUserRepositories.layoutManager = layoutManager
        binding.rvUserRepositories.adapter = repositoryAdapter
        binding.rvUserRepositories.addItemDecoration(spacerItemDecoration)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        arguments?.getString(USERNAME)?.let {
            username = it
            userViewModel.loadUserDetail(username)
            repositoriesViewModel.loadUserRepositories(username)
        }
        arguments?.getString(PHOTO_URL)?.let {
            binding.ivPhoto.loadCircleImage(it)
        }
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
        binding.tvUsername.text = data.login
        binding.tvFollowers.text = if (data.followers >= 0) data.followers.toString() else "0"
        binding.tvFollowing.text = if (data.following >= 0) data.followers.toString() else "0"
        binding.tvLabelPublicRepos.text = if (data.public_repos >= 0) data.followers.toString() else "0"
        binding.tvCompany.text = data.company.ifBlank { getString(R.string.placeholder_not_found) }
        binding.tvLocation.text = data.location.ifBlank { getString(R.string.placeholder_not_found) }
        binding.tvBlog.text = data.blog.ifBlank { getString(R.string.placeholder_not_found) }
        binding.tvBlog.setOnClickListener {
            data.blog.takeIf { it.isNotBlank() }?.let {
                context?.let { url -> it.openUrlInBrowser(url) }
            }
        }
        val twitterLink = if (data.twitter_username.isNotBlank()){
            "https://www.twitter.com/${data.twitter_username}"
        } else {
            getString(R.string.placeholder_not_found)
        }
        binding.tvTwitter.text = twitterLink
        binding.tvTwitter.setOnClickListener {
            if (data.twitter_username.isNotBlank()){
                context?.let { context -> twitterLink.openUrlInBrowser(context) }
            }
        }
        binding.tvEmail.text = data.email.ifBlank { getString(R.string.placeholder_not_found) }
        binding.tvEmail.setOnClickListener {
            if (data.email.isNotBlank()){
                context?.let { context -> data.email.sendEmailToThisAddress(context) }
            }
        }

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
        const val PHOTO_URL = "PHOTO_URL"
        const val REPOSITORY_QUANTITY_IN_LIST = 5
        const val USER_PHOTO_IMAGE_VIEW = "USER_PHOTO_IMAGE_VIEW"
    }
}