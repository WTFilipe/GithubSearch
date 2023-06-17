package com.example.githubsearch.ui.user.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.githubsearch.BaseFragment
import com.example.githubsearch.databinding.FragmentUserDetailBinding
import com.example.githubsearch.domain.UserViewModel
import com.example.githubsearch.hide
import com.example.githubsearch.show
import com.example.githubsearch.ui.models.UIState
import com.example.githubsearch.ui.models.UserUIModel

class UserDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        arguments?.getString(USERNAME)?.let {
            username = it
            userViewModel.loadUserDetail(username)
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
    }
}