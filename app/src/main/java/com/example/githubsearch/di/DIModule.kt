package com.example.githubsearch.di

import com.example.githubsearch.data.remote.GithubService
import com.example.githubsearch.data.remote.IUserDataSource
import com.example.githubsearch.data.remote.IUserRepository
import com.example.githubsearch.data.remote.RetrofitConfig
import com.example.githubsearch.data.remote.UserRemoteDataSource
import com.example.githubsearch.data.remote.UserRepository
import com.example.githubsearch.domain.GetUserListUseCase
import com.example.githubsearch.domain.IGetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Provides
    @Singleton
    fun provideGithubService() : GithubService {
        return RetrofitConfig.service
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(githubService: GithubService) : IUserDataSource{
        return UserRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: IUserDataSource) : IUserRepository{
        return UserRepository(userRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userRepository: IUserRepository) : IGetUserListUseCase{
        return GetUserListUseCase(userRepository)
    }

}