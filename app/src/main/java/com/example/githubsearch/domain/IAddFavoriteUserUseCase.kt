package com.example.githubsearch.domain

import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IAddFavoriteUserUseCase {
    suspend fun execute(userUIModel: UserUIModel) : Flow<Long>
}