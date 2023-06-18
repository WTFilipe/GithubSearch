package com.example.githubsearch.ui.user

import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
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

    override fun loadData() {
        userViewModel.loadFavoriteUserList()
    }

    override fun onUserListItemClicked(username: String, photoURL: String, imageView: ImageView) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to username,
            UserDetailFragment.PHOTO_URL to photoURL
        )
        val extras = FragmentNavigatorExtras(imageView to UserDetailFragment.USER_PHOTO_IMAGE_VIEW)
        view?.findNavController()?.navigate(R.id.action_usersListFragment_to_userDetailFragment, bundle, null, extras)
    }

    override fun onSuccess(data: List<UserOnListUIModel>) {
        if (data.isEmpty()){
            binding.emptyLayout.errorText.text = getString(R.string.feedback_empty_response_favorite)
            binding.emptyLayout.root.show()
            binding.usersList.hide()
        }

        binding.errorLayout.root.hide()
        binding.loadingLayout.hide()
    }
}