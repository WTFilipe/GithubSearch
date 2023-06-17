package com.example.githubsearch.data.remote

import com.example.githubsearch.data.remote.model.RepositoryOnListRemoteModel
import com.example.githubsearch.data.remote.model.UserOnListRemoteModel
import com.example.githubsearch.data.remote.model.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users")
    suspend fun getUserList(): List<UserOnListRemoteModel>

    @GET("users/{user}")
    suspend fun getUserDetail(@Path("user") user: String): UserRemoteModel

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String?): List<RepositoryOnListRemoteModel>
}