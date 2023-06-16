package com.example.githubsearch.data.remote

import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository constructor(
    private val remoteDataSource: UserRemoteDataSource
){
}