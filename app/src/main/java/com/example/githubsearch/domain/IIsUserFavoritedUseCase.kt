package com.example.githubsearch.domain

import kotlinx.coroutines.flow.Flow

interface IIsUserFavoritedUseCase {
    suspend fun execute(id: Int) : Flow<Boolean>
}