package com.example.githubsearch.domain

import com.example.githubsearch.data.IUserRepository
import com.example.githubsearch.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUsersListUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetFavoriteUsersListUseCase{

    override suspend fun execute(): Flow<List<UserOnListUIModel>> = userRepository.getFavoriteUsers()
}