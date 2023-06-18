package com.example.githubsearch.ui.user.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import retrofit2.HttpException
import java.net.UnknownHostException

class UserDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var username: String
    private lateinit var repositoryAdapter: RepositoryListAdapter
    private val userViewModel: UserViewModel by viewModels()
    private val repositoriesViewModel: RepositoryViewModel by viewModels()
    private var isUserFavorited = false

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
        } ?: onError()
    }

    private fun setupObservers() {
        userViewModel.user.observe(viewLifecycleOwner){
            when(it){
                is UIState.Error -> onError(it.cause)
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

        userViewModel.isUserFavorited.observe(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> {}
                is UIState.Error -> {
                    isUserFavorited = false
                    binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite_off) })
                }
                is UIState.Success -> {
                    if (!it.data){
                        isUserFavorited = false
                        binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite_off) })
                    } else {
                        isUserFavorited = true
                        binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite) })
                    }
                }
            }
        }

        userViewModel.addFavoriteSuccess.observe(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> {
                    binding.ivFavoriteIcon.hide()
                    binding.pbFavorite.show()
                }
                is UIState.Error -> {
                    binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite_off) })
                    binding.ivFavoriteIcon.show()
                    binding.pbFavorite.hide()
                }
                is UIState.Success ->{
                    binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite) })
                    binding.ivFavoriteIcon.show()
                    binding.pbFavorite.hide()
                }
            }
        }

        userViewModel.removeFavoriteSuccess.observe(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> {
                    binding.ivFavoriteIcon.hide()
                    binding.pbFavorite.show()
                }
                is UIState.Error -> {
                    binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite) })
                    binding.ivFavoriteIcon.show()
                    binding.pbFavorite.hide()
                }
                is UIState.Success ->{
                    binding.ivFavoriteIcon.setImageDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_favorite_off) })
                    binding.ivFavoriteIcon.show()
                    binding.pbFavorite.hide()
                }
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
        userViewModel.loadIsUserFavorited(data.id)

        binding.tvName.text = data.name
        binding.tvBio.text = data.bio
        binding.tvUsername.text = data.login
        binding.ivPhoto.loadCircleImage(data.avatar_url, context?.let { ContextCompat.getDrawable(it, R.drawable.ic_personal_user) })
        binding.tvFollowers.text = if (data.followers >= 0) data.followers.toString() else "0"
        binding.tvFollowing.text = if (data.following >= 0) data.followers.toString() else "0"
        binding.tvPublicRepos.text = if (data.public_repos >= 0) data.followers.toString() else "0"
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
        binding.errorLayout.root.hide()

        binding.ivFavoriteIcon.setOnClickListener {
            if (isUserFavorited){
                userViewModel.deleteFavoriteUser(data)
            } else {
                userViewModel.addFavoriteUser(data)
            }
        }
    }

    private fun onLoading() {
        binding.loadingLayout.show()
        binding.errorLayout.root.hide()
    }

    private fun onError(cause: Throwable? = null) {
        binding.loadingLayout.hide()
        binding.errorLayout.root.show()

        when {
            cause is HttpException && cause.code() == UserViewModel.RESULT_NOT_FOUND_CODE -> binding.errorLayout.errorText.text = getString(R.string.feedback_no_user_found)
            cause is UnknownHostException -> binding.errorLayout.errorText.text = getString(R.string.feedback_no_internet_connection)
            else -> binding.errorLayout.errorText.text = getString(R.string.feedback_generic_error)
        }
    }

    companion object {
        const val USERNAME = "USERNAME"
        const val REPOSITORY_QUANTITY_IN_LIST = 5
    }
}