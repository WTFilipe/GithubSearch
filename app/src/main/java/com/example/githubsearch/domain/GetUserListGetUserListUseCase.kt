package com.example.githubsearch.domain

import com.example.githubsearch.data.remote.IUserRepository
import com.example.githubsearch.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListGetUserListUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetUserListUseCase{

    override suspend fun execute(): Flow<List<UserOnListUIModel>> = userRepository.getUserList()
}