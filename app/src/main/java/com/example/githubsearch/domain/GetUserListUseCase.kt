package com.example.githubsearch.domain

import com.example.githubsearch.data.remote.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    fun execute(){

    }
}