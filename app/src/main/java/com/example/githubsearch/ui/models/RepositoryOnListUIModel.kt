package com.example.githubsearch.ui.models

data class RepositoryOnListUIModel(
    val name: String,
    val created_at: String,
    val description: String,
    val stargazers_count: Int,
    val watchers_count: Int,
    val url: String
)