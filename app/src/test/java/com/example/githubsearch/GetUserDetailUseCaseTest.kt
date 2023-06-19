package com.example.githubsearch

import com.example.githubsearch.data.IUserRepository
import com.example.githubsearch.domain.GetUserDetailUseCase
import com.example.githubsearch.ui.models.UserUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserDetailUseCaseTest {

    @Mock
    private lateinit var userRepository: IUserRepository

    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getUserDetailUseCase = GetUserDetailUseCase(userRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `execute should return user detail flow`() = runTest {
        // Arrange
        val username = "Filipe"
        val expectedUser = UserUIModel(
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            0,
            false,
            1,
            "",
            username,
            "",
            0,
            false,
            "",
            "",
            "",
            ""
        )
        val userFlow = flowOf(expectedUser)
        Mockito.`when`(userRepository.getUserDetail(username)).thenReturn(userFlow)

        // Act
        val result = getUserDetailUseCase.execute(username).toList()

        // Assert
        assertEquals(username, result[0].login)
        Mockito.verify(userRepository).getUserDetail(username)
    }
}
