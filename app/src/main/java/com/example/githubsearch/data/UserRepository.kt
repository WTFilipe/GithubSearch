package com.example.githubsearch.data

import com.example.githubsearch.data.local.IUserLocalDataSource
import com.example.githubsearch.data.remote.user.IUserRemoteDataSource
import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: IUserRemoteDataSource,
    private val localDataSource: IUserLocalDataSource
): IUserRepository {
    override suspend fun getUserList(): Flow<List<UserOnListUIModel>> = remoteDataSource.getUserList().flowOn(Dispatchers.IO)

    override suspend fun getUserDetail(userName: String): Flow<UserUIModel> = remoteDataSource.getUserDetail(userName).flowOn(Dispatchers.IO)
    override suspend fun getFavoriteUsers(): Flow<List<UserOnListUIModel>> = localDataSource.getFavoritesUsersList().flowOn(Dispatchers.IO)

    override suspend fun addFavoriteUser(user: UserUIModel) : Flow<Long> = localDataSource.addFavoriteUser(user).flowOn(Dispatchers.IO)
    override suspend fun removeFavoriteUser(user: UserUIModel) : Flow<Int> = localDataSource.removeFavoriteUser(user).flowOn(Dispatchers.IO)
    override suspend fun isUserFavorited(id: Int): Flow<Boolean> = localDataSource.isUserFavorited(id).flowOn(Dispatchers.IO)
}