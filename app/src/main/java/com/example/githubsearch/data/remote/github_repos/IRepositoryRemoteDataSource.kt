package com.example.githubsearch.data.remote.github_repos

import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IRepositoryRemoteDataSource {
    suspend fun getUserRepositoriesList(username: String) : Flow<List<RepositoryOnListUIModel>>
}