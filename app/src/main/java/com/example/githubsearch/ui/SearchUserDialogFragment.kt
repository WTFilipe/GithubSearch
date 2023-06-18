package com.example.githubsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentSearchUserDialogBinding
import com.example.githubsearch.ui.user.detail.UserDetailFragment

class SearchUserDialogFragment: DialogFragment() {

    private lateinit var binding: FragmentSearchUserDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchUserDialogBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tietSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                val searchTerm = binding.tietSearch.text?.toString()

                if (searchTerm.isNullOrBlank()){
                    binding.tilSearch.error = getString(R.string.feedback_search_empty_field)
                } else {
                    goToUserDetailFragment(searchTerm)
                }

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

        binding.tietSearch.doOnTextChanged { _, _, _, _ ->
            binding.tilSearch.error = null
        }
    }

    private fun goToUserDetailFragment(searchTerm: String) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to searchTerm,
        )
        findNavController(this).navigate(R.id.action_searchUserDialogFragment_to_userDetailFragment, bundle)
    }
}