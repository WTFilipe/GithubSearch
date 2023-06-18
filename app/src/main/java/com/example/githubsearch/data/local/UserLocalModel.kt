package com.example.githubsearch.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubsearch.ui.models.UserOnListUIModel

@Entity
data class UserLocalModel(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "photo_url") val photoURL: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "user_type") val type: String
) {
    fun toUIModel() : UserOnListUIModel {
        return UserOnListUIModel(
            avatar_url = photoURL,
            id = uid,
            login = name,
            type = type
        )
    }
}