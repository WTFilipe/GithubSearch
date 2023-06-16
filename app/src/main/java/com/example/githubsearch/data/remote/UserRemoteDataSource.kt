package com.example.githubsearch.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) {
}