package com.example.githubsearch.data.remote

import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: IUserDataSource
): IUserRepository {
    override suspend fun getUserList(): Flow<List<UserOnListUIModel>> = remoteDataSource.getUserList().flowOn(Dispatchers.IO)

    override suspend fun getUserDetail(userName: String): Flow<UserUIModel> = remoteDataSource.getUserDetail(userName).flowOn(Dispatchers.IO)
}