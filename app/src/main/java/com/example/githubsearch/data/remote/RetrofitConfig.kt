package com.example.githubsearch.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitConfig {
    var service: GithubService = getGithubService()

    private fun getRetrofitBuild() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getGithubService() : GithubService {
        return getRetrofitBuild().create(GithubService::class.java)
    }
}