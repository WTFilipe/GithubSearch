package com.example.githubsearch.data.remote

import com.example.githubsearch.data.remote.model.UserRemoteModel
import com.example.githubsearch.data.remote.model.RepositoryRemoteModel
import com.example.githubsearch.data.remote.model.UserOnListRemoteModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users")
    suspend fun getUserList(): List<UserOnListRemoteModel>

    @GET("users/{user}")
    suspend fun getUserDetail(@Path("user") user: String): Flow<UserRemoteModel?>

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String?): Flow<List<RepositoryRemoteModel?>?>
}