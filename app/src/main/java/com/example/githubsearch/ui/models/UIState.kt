package com.example.githubsearch.ui.models


sealed class UIState<out T: Any> {
    data class Success<out T: Any>(val data: T): UIState<T>()
    data class Error(val msg: String? = null, val cause: Throwable? = null): UIState<Nothing>()
    object Loading : UIState<Nothing>()
}