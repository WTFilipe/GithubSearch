package com.example.githubsearch.data.local

import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IUserLocalDataSource {
    suspend fun getFavoritesUsersList() : Flow<List<UserOnListUIModel>>

    suspend fun addFavoriteUser(user: UserUIModel) : Flow<Long>

    suspend fun removeFavoriteUser(user: UserUIModel) : Flow<Int>
    suspend fun isUserFavorited(id: Int) : Flow<Boolean>
}