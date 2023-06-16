package com.example.githubsearch.ui.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubsearch.BaseFragment
import com.example.githubsearch.databinding.FragmentUsersListBinding
import com.example.githubsearch.domain.UserViewModel
import com.example.githubsearch.hide
import com.example.githubsearch.show
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserOnListUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersListBinding
    private lateinit var userListAdapter: UserListAdapter
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater)

        setupUserRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        userViewModel.loadUserList()
    }

    private fun setupObservers() {
        userViewModel.usersList.observe(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> onLoading()
                is UIState.Error -> onError()
                is UIState.Success -> onSuccess(it.data)
            }
        }
    }

    private fun onSuccess(data: List<UserOnListUIModel>) {
        userListAdapter.setUserList(data)
        binding.errorLayout.hide()
        binding.loadingLayout.hide()
        binding.usersList.show()
    }

    private fun onLoading() {
        binding.errorLayout.hide()
        binding.loadingLayout.show()
        binding.usersList.hide()
    }

    private fun onError() {
        binding.errorLayout.show()
        binding.loadingLayout.hide()
        binding.usersList.hide()
    }

    private fun setupUserRecyclerView() {
        userListAdapter = UserListAdapter()
        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        binding.usersList.layoutManager = layoutManager
        binding.usersList.adapter = userListAdapter
    }

    companion object {
        fun newInstance(): UsersListFragment {
            return UsersListFragment()
        }
    }

}