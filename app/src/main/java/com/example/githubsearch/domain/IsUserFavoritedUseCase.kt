package com.example.githubsearch.domain

import com.example.githubsearch.data.IUserRepository
import javax.inject.Inject

class IsUserFavoritedUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IIsUserFavoritedUseCase{
    override suspend fun execute(id: Int) = userRepository.isUserFavorited(id)
}