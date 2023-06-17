package com.example.githubsearch.data.remote.user

import com.example.githubsearch.ui.models.UserOnListUIModel
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    suspend fun getUserList() : Flow<List<UserOnListUIModel>>
    suspend fun getUserDetail(userName: String) : Flow<UserUIModel>
}