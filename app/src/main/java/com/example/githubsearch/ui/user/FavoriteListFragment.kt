package com.example.githubsearch.ui.user

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.githubsearch.R
import com.example.githubsearch.hide
import com.example.githubsearch.show
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.user.detail.UserDetailFragment
import com.example.githubsearch.ui.user.list.UsersListFragment

class FavoriteListFragment() : UsersListFragment() {


    override fun setupObservers() {
        userViewModel.favoriteUsersList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> onLoading()
                is UIState.Error -> onError(it.cause)
                is UIState.Success -> onSuccess(it.data)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabSearch.hide()
    }

    override fun loadData() {
        userViewModel.loadFavoriteUserList()
    }

    override fun onUserListItemClicked(username: String) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to username,
        )
        view?.findNavController()?.navigate(R.id.action_favoriteListFragment_to_userDetailFragment, bundle)
    }

    override fun onSuccess(data: List<UserOnListUIModel>) {
        if (data.isEmpty()){
            binding.emptyLayout.errorText.text = getString(R.string.feedback_empty_response_favorite)
            binding.emptyLayout.root.show()
            binding.usersList.hide()
            binding.loadingLayout.hide()
            binding.errorLayout.root.hide()

        } else {
            userListAdapter.setUserList(data)
            binding.errorLayout.root.hide()
            binding.emptyLayout.root.hide()
            binding.loadingLayout.hide()
            binding.usersList.show()
        }
    }

    override fun onLoading() {
        binding.errorLayout.root.hide()
        binding.emptyLayout.root.hide()
        binding.loadingLayout.show()
        binding.usersList.hide()
    }

    override fun onError(cause: Throwable?) {
        binding.errorLayout.errorText.text = getString(R.string.feedback_generic_error)

        binding.errorLayout.root.show()
        binding.emptyLayout.root.hide()
        binding.loadingLayout.hide()
        binding.usersList.hide()
    }


}