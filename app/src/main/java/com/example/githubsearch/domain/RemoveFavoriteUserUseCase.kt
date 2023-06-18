package com.example.githubsearch.domain

import com.example.githubsearch.data.IUserRepository
import com.example.githubsearch.ui.models.UserUIModel
import javax.inject.Inject

class RemoveFavoriteUserUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IRemoveFavoriteUserUseCase{
    override suspend fun execute(userUIModel: UserUIModel) = userRepository.removeFavoriteUser(userUIModel)
}