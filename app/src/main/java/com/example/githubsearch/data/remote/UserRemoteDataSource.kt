package com.example.githubsearch.data.remote

import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : IUserDataSource{
    override suspend fun getUserList(): Flow<List<UserOnListUIModel>> = flow {
        val listInUIModel = githubService.getUserList().map {
            it.toUIModel()
        }
        emit(listInUIModel)
    }

    override suspend fun getUserDetail(): Flow<UserUIModel> {
        TODO("Not yet implemented")
    }

}