package com.example.githubsearch.data

import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    suspend fun getUserList() : Flow<List<UserOnListUIModel>>
    suspend fun getUserDetail(userName: String) : Flow<UserUIModel>
    suspend fun getFavoriteUsers(): Flow<List<UserOnListUIModel>>
    suspend fun addFavoriteUser(user: UserUIModel) : Flow<Long>
    suspend fun removeFavoriteUser(user: UserUIModel) : Flow<Int>
}