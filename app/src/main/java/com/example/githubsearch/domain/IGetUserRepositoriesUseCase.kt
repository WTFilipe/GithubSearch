package com.example.githubsearch.domain

import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IGetUserRepositoriesUseCase {
    suspend fun execute(username: String) : Flow<List<RepositoryOnListUIModel>>
}