package com.example.githubsearch

import com.example.githubsearch.data.local.UserDao
import com.example.githubsearch.data.local.UserLocalDataSource
import com.example.githubsearch.data.local.UserLocalModel
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
class UserLocalDataSourceTest {

    @Mock
    private lateinit var userDao: UserDao

    private lateinit var userLocalDataSource: UserLocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userLocalDataSource = UserLocalDataSource(userDao)
    }

    @Test
    fun `getFavoritesUsersList should return flow of favorite user list`() = runTest {
        // Arrange
        val userEntities = listOf(UserLocalModel(
            1,
            "",
            "",
            ""
        ))
        val userList = userEntities.map { it.toUIModel() }
        Mockito.`when`(userDao.getAll()).thenReturn(userEntities)

        // Act
        val result = userLocalDataSource.getFavoritesUsersList().toList()

        // Assert
        assertEquals(1, result.size)
        assertEquals(userList, result[0])
        Mockito.verify(userDao).getAll()
    }
}
