package com.example.githubsearch.data.remote.model

import com.example.githubsearch.ui.models.UserOnListUIModel

data class UserOnListRemoteModel(
    val avatar_url: String? = null,
    val events_url: String? = null,
    val followers_url: String? = null,
    val following_url: String = "",
    val gists_url: String? = null,
    val gravatar_id: String? = null,
    val html_url: String? = null,
    val id: Int = 0,
    val login: String? = null,
    val node_id: String? = null,
    val organizations_url: String? = null,
    val received_events_url: String? = null,
    val repos_url: String? = null,
    val site_admin: Boolean? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val type: String? = null,
    val url: String? = null
) {
    fun toUIModel() : UserOnListUIModel{
        return UserOnListUIModel(
            avatar_url ?: "",
            id,
            login ?: "",
            type ?: "",
        )
    }
}