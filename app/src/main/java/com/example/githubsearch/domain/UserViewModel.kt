package com.example.githubsearch.domain

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
): ViewModel() {

    fun getUserDetail(userName: String){

    }

    fun getUserList(){

    }
}