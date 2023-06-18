package com.example.githubsearch.di

import android.content.Context
import androidx.room.Room
import com.example.githubsearch.data.local.AppDatabase
import com.example.githubsearch.data.local.UserDao
import com.example.githubsearch.data.GithubReposRepository
import com.example.githubsearch.data.remote.GithubService
import com.example.githubsearch.data.IGithubReposRepository
import com.example.githubsearch.data.remote.github_repos.IRepositoryRemoteDataSource
import com.example.githubsearch.data.remote.user.IUserRemoteDataSource
import com.example.githubsearch.data.IUserRepository
import com.example.githubsearch.data.remote.github_repos.RepositoriesRemoteRemoteDataSource
import com.example.githubsearch.data.remote.RetrofitConfig
import com.example.githubsearch.data.remote.user.UserRemoteDataSource
import com.example.githubsearch.data.UserRepository
import com.example.githubsearch.data.local.IUserLocalDataSource
import com.example.githubsearch.data.local.UserLocalDataSource
import com.example.githubsearch.domain.AddFavoriteUserUseCase
import com.example.githubsearch.domain.GetUserListUseCase
import com.example.githubsearch.domain.GetUserRepositoriesUseCase
import com.example.githubsearch.domain.IAddFavoriteUserUseCase
import com.example.githubsearch.domain.IGetUserListUseCase
import com.example.githubsearch.domain.IGetUserRepositoriesUseCase
import com.example.githubsearch.domain.IRemoveFavoriteUserUseCase
import com.example.githubsearch.domain.RemoveFavoriteUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideUserRemoteDataSource(githubService: GithubService) : IUserRemoteDataSource {
        return UserRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDao: UserDao) : IUserLocalDataSource {
        return UserLocalDataSource(userDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: IUserRemoteDataSource, userLocalDataSource: IUserLocalDataSource) : IUserRepository {
        return UserRepository(userRemoteDataSource, userLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userRepository: IUserRepository) : IGetUserListUseCase{
        return GetUserListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteUsersListUseCase(userRepository: IUserRepository) : IGetUserListUseCase{
        return GetUserListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideAddFavoriteUserUseCase(userRepository: IUserRepository) : IAddFavoriteUserUseCase{
        return AddFavoriteUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveFavoriteUserUseCase(userRepository: IUserRepository) : IRemoveFavoriteUserUseCase {
        return RemoveFavoriteUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideRepositoryRemoteDataSource(githubService: GithubService): IRepositoryRemoteDataSource {
        return RepositoriesRemoteRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideGithubReposRepository(reposRemoteDataSource: IRepositoryRemoteDataSource) : IGithubReposRepository {
        return GithubReposRepository(reposRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserRepositoriesUseCase(githubReposRepository: IGithubReposRepository) : IGetUserRepositoriesUseCase{
        return GetUserRepositoriesUseCase(githubReposRepository)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }

}