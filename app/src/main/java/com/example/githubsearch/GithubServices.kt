package com.example.githubsearch

import com.example.githubsearch.remote.model.GithubUser
import com.example.githubsearch.remote.model.GithubRepository
import com.example.githubsearch.remote.model.GithubUserOnList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users")
    fun getUserList(): Call<List<GithubUserOnList?>?>?

    @GET("users/{user}")
    fun getUserDetail(@Path("user") user: String): Call<GithubUser?>?

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String?): Call<List<GithubRepository?>?>?
}