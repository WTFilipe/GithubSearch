package com.example.githubsearch.remote

import com.example.githubsearch.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitConfig {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: GithubService = retrofit.create(GithubService::class.java)
}