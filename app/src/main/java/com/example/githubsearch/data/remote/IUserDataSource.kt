package com.example.githubsearch.data.remote

import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IUserDataSource {
    suspend fun getUserList() : Flow<List<UserOnListUIModel>>
    suspend fun getUserDetail() : Flow<UserUIModel>
}