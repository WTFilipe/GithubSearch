package com.example.githubsearch.di

import com.example.githubsearch.data.remote.github_repos.GithubReposRepository
import com.example.githubsearch.data.remote.GithubService
import com.example.githubsearch.data.remote.github_repos.IGithubReposRepository
import com.example.githubsearch.data.remote.github_repos.IRepositoryDataSource
import com.example.githubsearch.data.remote.user.IUserDataSource
import com.example.githubsearch.data.remote.user.IUserRepository
import com.example.githubsearch.data.remote.github_repos.RepositoriesRemoteDataSource
import com.example.githubsearch.data.remote.RetrofitConfig
import com.example.githubsearch.data.remote.user.UserRemoteDataSource
import com.example.githubsearch.data.remote.user.UserRepository
import com.example.githubsearch.domain.GetUserListUseCase
import com.example.githubsearch.domain.GetUserRepositoriesUseCase
import com.example.githubsearch.domain.IGetUserListUseCase
import com.example.githubsearch.domain.IGetUserRepositoriesUseCase
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
    fun provideUserRemoteDataSource(githubService: GithubService) : IUserDataSource {
        return UserRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: IUserDataSource) : IUserRepository {
        return UserRepository(userRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userRepository: IUserRepository) : IGetUserListUseCase{
        return GetUserListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideRepositoryRemoteDataSource(githubService: GithubService): IRepositoryDataSource {
        return RepositoriesRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideGithubReposRepository(reposRemoteDataSource: IRepositoryDataSource) : IGithubReposRepository {
        return GithubReposRepository(reposRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserRepositoriesUseCase(githubReposRepository: IGithubReposRepository) : IGetUserRepositoriesUseCase{
        return GetUserRepositoriesUseCase(githubReposRepository)
    }

}