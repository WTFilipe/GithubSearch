package com.example.githubsearch.domain

import com.example.githubsearch.data.IUserRepository
import com.example.githubsearch.ui.models.UserUIModel
import javax.inject.Inject

class AddFavoriteUserUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IAddFavoriteUserUseCase{
    override suspend fun execute(userUIModel: UserUIModel) = userRepository.addFavoriteUser(userUIModel)
}