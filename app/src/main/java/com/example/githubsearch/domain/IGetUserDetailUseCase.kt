package com.example.githubsearch.domain

import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IGetUserDetailUseCase {
    suspend fun execute(username: String) : Flow<UserUIModel>
}