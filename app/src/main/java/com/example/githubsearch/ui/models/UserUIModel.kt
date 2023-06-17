package com.example.githubsearch.ui.models

data class UserUIModel(
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val company: String,
    val created_at: String,
    val email: String,
    val followers: Int,
    val following: Int,
    val hireable: Boolean,
    val id: Int,
    val location: String,
    val login: String,
    val name: String,
    val public_repos: Int,
    val site_admin: Boolean,
    val twitter_username: String,
    val type: String,
    val updated_at: String,
    val url: String
)
