package com.example.githubsearch.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM userlocalmodel")
    fun getAll(): List<UserLocalModel>

    @Delete
    fun deleteUser(userLocalModel: UserLocalModel) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userLocalModel: UserLocalModel) : Long


}