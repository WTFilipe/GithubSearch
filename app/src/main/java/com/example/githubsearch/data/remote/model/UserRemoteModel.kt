package com.example.githubsearch.data.remote.model

import com.example.githubsearch.ui.models.UserUIModel

data class UserRemoteModel(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val created_at: String?,
    val email: String?,
    val events_url: String?,
    val followers: Int?,
    val followers_url: String?,
    val following: Int?,
    val following_url: String?,
    val gists_url: String?,
    val gravatar_id: String?,
    val hireable: Boolean?,
    val html_url: String?,
    val id: Int,
    val location: String?,
    val login: String?,
    val name: String?,
    val node_id: String?,
    val organizations_url: String?,
    val public_gists: Int?,
    val public_repos: Int?,
    val received_events_url: String?,
    val repos_url: String?,
    val site_admin: Boolean?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val twitter_username: String?,
    val type: String?,
    val updated_at: String?,
    val url: String?
) {
    fun toUIModel(): UserUIModel{
        return UserUIModel(
             avatar_url ?: "",
             bio ?: "",
             blog ?: "",
             company ?: "",
             created_at ?: "",
             email ?: "",
             followers ?: -1,
             following ?: -1,
             hireable ?: false,
             id,
             location ?: "",
             login ?: "",
             name ?: "",
             public_repos ?: -1,
             site_admin ?: false,
             twitter_username ?: "",
             type ?: "",
             updated_at ?: "",
             url ?: ""
         )
    }
}