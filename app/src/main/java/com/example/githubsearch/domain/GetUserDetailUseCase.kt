package com.example.githubsearch.domain

import com.example.githubsearch.data.remote.IUserRepository
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetUserDetailUseCase{

    override suspend fun execute(username: String): Flow<UserUIModel> = userRepository.getUserDetail(username)
}