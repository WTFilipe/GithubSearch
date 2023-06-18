package com.example.githubsearch.data

import com.example.githubsearch.data.remote.github_repos.IRepositoryRemoteDataSource
import com.example.githubsearch.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubReposRepository @Inject constructor(
    private val remoteDataSource: IRepositoryRemoteDataSource
): IGithubReposRepository {

    override suspend fun getUserRepositoriesList(username: String): Flow<List<RepositoryOnListUIModel>> = remoteDataSource.getUserRepositoriesList(username)

}