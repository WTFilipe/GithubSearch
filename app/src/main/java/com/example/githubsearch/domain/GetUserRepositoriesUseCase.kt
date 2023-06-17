package com.example.githubsearch.domain

import com.example.githubsearch.data.remote.github_repos.IGithubReposRepository
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val githubReposRepository: IGithubReposRepository
): IGetUserRepositoriesUseCase{

    override suspend fun execute(username: String) = githubReposRepository.getUserRepositoriesList(username)
}