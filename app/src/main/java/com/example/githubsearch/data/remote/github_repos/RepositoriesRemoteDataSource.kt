package com.example.githubsearch.data.remote.github_repos

import com.example.githubsearch.data.remote.GithubService
import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoriesRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : IRepositoryDataSource {

    override suspend fun getUserRepositoriesList(username: String): Flow<List<RepositoryOnListUIModel>> = flow {
        val listInUIModel = githubService.getUserRepos(username).map { it.toUIModel() }
        emit(listInUIModel)
    }

}