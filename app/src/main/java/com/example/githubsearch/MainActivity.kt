package com.example.githubsearch

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.githubsearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = LayoutInflater.from(this)
        binding = ActivityMainBinding.inflate(inflater)
        setContentView(binding.root)

        setBottomNavigationListeners()
    }

    private fun setBottomNavigationListeners() {
        binding.bnvMainActivity.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.item_users -> {
                    findNavController(binding.navHostFragment.id).navigate(R.id.go_to_usersListFragment)
                    true
                }
                R.id.item_favorite -> {
                    findNavController(binding.navHostFragment.id).navigate(R.id.go_to_favoriteUsersListFragment)
                    true
                }
                else -> {
                    false
                }

            }
        }
    }
}