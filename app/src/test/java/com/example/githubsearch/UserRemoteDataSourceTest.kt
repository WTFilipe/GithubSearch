package com.example.githubsearch

import com.example.githubsearch.data.remote.GithubService
import com.example.githubsearch.data.remote.model.UserOnListRemoteModel
import com.example.githubsearch.data.remote.model.UserRemoteModel
import com.example.githubsearch.data.remote.user.UserRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UserRemoteDataSourceTest {

    @Mock
    private lateinit var githubService: GithubService

    private lateinit var userRemoteDataSource: UserRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userRemoteDataSource = UserRemoteDataSource(githubService)
    }

    @Test
    fun `getUserList should return flow of user list`() = runTest {
        // Arrange
        val userList = listOf(UserOnListRemoteModel(
            null,
            null,
            null,
            "",
            null,
            null,
            null,
            1,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ))
        Mockito.`when`(githubService.getUserList()).thenReturn(userList)

        // Act
        val result = userRemoteDataSource.getUserList().toList()

        // Assert
        assertEquals(1, result.size)
        assertEquals(userList.map { it.toUIModel() }, result[0])
        Mockito.verify(githubService).getUserList()
    }

    @Test
    fun `getUserDetail should return flow of user detail`() = runTest {
        // Arrange
        val username = "Filipe"
        val userDetail = UserRemoteModel(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            1,
            null,
            null,
            username,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        Mockito.`when`(githubService.getUserDetail(username)).thenReturn(userDetail)

        // Act
        val result = userRemoteDataSource.getUserDetail(username).toList()

        // Assert
        assertEquals(1, result.size)
        assertEquals(userDetail.toUIModel().name, result[0].name)
        Mockito.verify(githubService).getUserDetail(username)
    }
}
