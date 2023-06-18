package com.example.githubsearch.data.remote.user

import com.example.githubsearch.data.remote.GithubService
import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : IUserRemoteDataSource {
    override suspend fun getUserList(): Flow<List<UserOnListUIModel>> = flow {
        val listInUIModel = githubService.getUserList().map {
            it.toUIModel()
        }
        emit(listInUIModel)
    }

    override suspend fun getUserDetail(username: String): Flow<UserUIModel> = flow {
        val userInUIModel = githubService.getUserDetail(username).toUIModel()
        emit(userInUIModel)
    }

}