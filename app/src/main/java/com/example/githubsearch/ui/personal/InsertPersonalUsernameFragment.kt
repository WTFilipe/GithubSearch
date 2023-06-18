package com.example.githubsearch.ui.personal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentInsertPersonalUsernameBinding
import com.example.githubsearch.ui.user.detail.UserDetailFragment

class InsertPersonalUsernameFragment : Fragment() {
    private lateinit var binding: FragmentInsertPersonalUsernameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getSavedUsername()?.let {
            goToUserDetailFragment(it)
        }
        binding = FragmentInsertPersonalUsernameBinding.inflate(inflater)
        setupListeners()
        return binding.root
    }

    private fun getSavedUsername(): String? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
        return sharedPref.getString(PersonalDetailFragment.SAVED_USER, null)
    }

    private fun setupListeners() {
        binding.tietUsername.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                val searchTerm = binding.tietUsername.text?.toString()

                if (searchTerm.isNullOrBlank()){
                    binding.tietUsername.error = getString(R.string.feedback_search_empty_field)
                } else {
                    goToUserDetailFragment(searchTerm)
                }

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    private fun goToUserDetailFragment(username: String) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to username,
        )
        NavHostFragment.findNavController(this).navigate(R.id.action_insertPersonalUsernameFragment_to_personalDetailFragment, bundle)
    }

}