package com.example.githubsearch.ui.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.githubsearch.BaseFragment
import com.example.githubsearch.databinding.FragmentUsersListBinding
import com.example.githubsearch.domain.UserViewModel
import com.example.githubsearch.ui.models.UserUI
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

    private fun setupUserRecyclerView() {
        userListAdapter = UserListAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.usersList.layoutManager = layoutManager
        binding.usersList.adapter = userListAdapter

        userListAdapter.setUserList(
            listOf(
                UserUI(1, "Filipe"),
                UserUI(1, "Filipe"),
                UserUI(1, "Filipe"),
                UserUI(1, "Filipe"),
                UserUI(1, "Filipe"),
            )
        )

    }

    companion object {
        fun newInstance(): UsersListFragment {
            return UsersListFragment()
        }
    }

}