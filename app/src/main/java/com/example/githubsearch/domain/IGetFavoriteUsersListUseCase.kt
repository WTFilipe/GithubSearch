package com.example.githubsearch.domain

import com.example.githubsearch.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IGetFavoriteUsersListUseCase {
    suspend fun execute() : Flow<List<UserOnListUIModel>>
}