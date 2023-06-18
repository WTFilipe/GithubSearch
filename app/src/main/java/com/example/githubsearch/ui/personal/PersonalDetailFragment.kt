package com.example.githubsearch.ui.personal

import android.content.Context
import android.os.Bundle
import android.system.Os.remove
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.githubsearch.R
import com.example.githubsearch.domain.UserViewModel
import com.example.githubsearch.getTintedDrawable
import com.example.githubsearch.hide
import com.example.githubsearch.show
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserUIModel
import com.example.githubsearch.ui.repository.list.RepositoryListFragment
import com.example.githubsearch.ui.user.detail.UserDetailFragment
import retrofit2.HttpException
import java.net.UnknownHostException

class PersonalDetailFragment : UserDetailFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupExitIcon()
        setupListeners()
    }

    private fun setupExitIcon() {
        binding.ivFavoriteIcon.hide()
        binding.pbFavorite.hide()
        context?.let {
            binding.ivFavoriteIcon.setImageDrawable(it.getTintedDrawable(R.drawable.ic_exit))
            binding.ivFavoriteIcon.show()
        }
    }

    private fun setupListeners() {
        binding.ivFavoriteIcon.setOnClickListener {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            with (sharedPref?.edit()) {
                remove(SAVED_USER)
                this?.apply()
                findNavController().popBackStack()
            }
        }
    }

    override fun setupObservers() {
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
                is UIState.Loading -> {}
                is UIState.Success -> onReposSuccess(it.data)
            }
        }

    }

    override fun onSuccess(data: UserUIModel) {
        super.onSuccess(data)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(SAVED_USER, username)
            apply()
        }
    }

    override fun onError(cause: Throwable?) {
        when {
            cause is HttpException && cause.code() == UserViewModel.RESULT_NOT_FOUND_CODE -> binding.errorLayout.errorText.text = getString(R.string.feedback_no_user_found)
            cause is UnknownHostException -> binding.errorLayout.errorText.text = getString(R.string.feedback_no_internet_connection)
            else -> {
                binding.errorLayout.root.hide()
                binding.loadingLayout.hide()
                binding.emptyLayout.root.show()
            }
        }
    }

    override fun goToRepositoryList() {
        val bundle = bundleOf(
            RepositoryListFragment.USERNAME to username,
        )
        findNavController().navigate(R.id.action_personalDetailFragment_to_repositoryListFragment, bundle)
    }


    companion object {
        const val SAVED_USER = "SAVED_USER"
    }
}